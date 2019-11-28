package br.gov.df.se.pdaf.services;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import br.gov.df.se.pdaf.dtos.LoginLdapDto;
import br.gov.df.se.pdaf.dtos.UsuarioLdapDto;
import br.gov.df.se.pdaf.exceptions.UnauthorizedException;

/**
 *
 * @author Matheus de Carvalho Sobrinho
 * @author 440310
 * @author Vinícius Orrú
 */
public class ActiveDirectoryFacade {

    private static final String MASTER_USER_DN;
    private static final String MASTER_PASSWORD;
    private static final String SEARCH_BASE;
    private static final String URL_LDAP;

    static {
        MASTER_USER_DN = "CN=sigep,OU=ContaServico,DC=SE,DC=GDFNET,DC=DF";
        MASTER_PASSWORD = "sedf0938*";
        SEARCH_BASE = "DC=SE,DC=GDFNET,DC=DF";
        URL_LDAP = "ldap://10.221.37.3:389";
    }

    private final String ldapUrl;
    private final String searchBase;

    public ActiveDirectoryFacade() {
        this.ldapUrl = URL_LDAP;
        this.searchBase = SEARCH_BASE;
    }

    public boolean hasGroup(String username, String password,
            String groupObjectName)
            throws NamingException {
        List<String> allGroups = getAllGroups(username, password);
        return allGroups.contains(groupObjectName);
    }

    public UsuarioLdapDto authenticateLdap(LoginLdapDto loginLdap) 
            throws NamingException {
        List<String> allGroups = getAllGroups(loginLdap.getMatricula(), loginLdap.getPassword());
        
        if (allGroups.isEmpty()) {
            throw new UnauthorizedException();
        }
        
        LdapName ldapName = new LdapName(allGroups.get(0));
        Rdn rdn = ldapName.getRdn(ldapName.size() - 1);
        String nome = (String) rdn.getValue();

        UsuarioLdapDto usuarioLdap = new UsuarioLdapDto(loginLdap.getMatricula(), nome.toUpperCase());
        
        return usuarioLdap;
    }

    public boolean authenticationOk(String username, String password)
            throws NamingException {
        List<String> allGroups = getAllGroups(username, password);
        return (!allGroups.isEmpty());
    }

    private List<String> getAllGroups(String username, String password)
            throws NamingException {
        List<String> result = new ArrayList<>();
        
        if (password.trim().equals("")) {
            throw new NamingException();
        }

        String attributeToLookup = "memberOf";

        /*
         * 1. Authenticate using master user.
         */
        DirContext ctx = authenticate();

        /*
         * 2. Searches by "sAMAccountName" to recover the full
         *    DN of the username trying to login.
         */
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setReturningAttributes(
                new String[]{"distinguishedName"});
        NamingEnumeration<SearchResult> searchResults = ctx.search(
                searchBase,
                String.format("(sAMAccountName=%s)", username),
                searchControls);
        if (!searchResults.hasMore()) {
            /*
             * If can't resolve DN, the user doesn't exists.
             */
            throw new NamingException();
        }
        SearchResult searchResult = searchResults.next();
        Attributes attributes = searchResult.getAttributes();
        Attribute attribute = attributes.get("distinguishedName");
        String userObject = (String) attribute.get();

        /*
         * 3. Authenticates to LDAP with the user, will throw if
         *    password is wrong.
         */
        ctx.close();

        ctx = authenticate(userObject, password);
        result.add(userObject);
        ctx.close();

        return result;

        /*
         * 4. Fetch all groups of user.
         */
 /*
        attributes = ctx.getAttributes(userObject,
                new String[]{attributeToLookup});

        NamingEnumeration<? extends Attribute> allAttributes
                = attributes.getAll();
        while (allAttributes.hasMoreElements()) {
            attribute = allAttributes.nextElement();
            int size = attribute.size();
            for (int i = 0; i < size; i++) {
                String attributeValue = (String) attribute.get(i);
                result.add(attributeValue);
            }
        }

        return result; */
    }

    private DirContext authenticate() throws NamingException {
        return authenticate(null, null);
    }

    private DirContext authenticate(String username, String password)
            throws NamingException {
        String initialContextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
        String securityAuthentication = "simple";

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
        env.put(Context.SECURITY_AUTHENTICATION, securityAuthentication);
        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_PRINCIPAL,
                username != null ? username : MASTER_USER_DN);
        env.put(Context.SECURITY_CREDENTIALS,
                password != null ? password : MASTER_PASSWORD);

        DirContext ctx = new InitialDirContext(env);

        return ctx;
    }
}
