package br.gov.df.se.pdaf.services;

import javax.naming.NamingException;

import org.springframework.stereotype.Service;

import br.gov.df.se.pdaf.dtos.LoginLdapDto;
import br.gov.df.se.pdaf.dtos.UsuarioLdapDto;

@Service
public class LoginService {

	public UsuarioLdapDto loginLdap(LoginLdapDto loginLdap) throws NamingException {
		return authenticateLDAPOnly(loginLdap);
	}

	private UsuarioLdapDto authenticateLDAPOnly (LoginLdapDto loginLdap) throws NamingException {
         return new ActiveDirectoryFacade().authenticateLdap(loginLdap);
    }
	
}
