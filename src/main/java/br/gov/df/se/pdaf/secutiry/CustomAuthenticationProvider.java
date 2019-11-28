package br.gov.df.se.pdaf.secutiry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.gov.df.se.pdaf.dtos.LoginLdapDto;
import br.gov.df.se.pdaf.dtos.UsuarioLdapDto;
import br.gov.df.se.pdaf.entities.Perfil;
import br.gov.df.se.pdaf.entities.PerfilPermissao;
import br.gov.df.se.pdaf.entities.Permissao;
import br.gov.df.se.pdaf.entities.Usuario;
import br.gov.df.se.pdaf.services.LoginService;
import br.gov.df.se.pdaf.services.UsuarioService;

/**
 * @author Matheus de Carvalho Sobrinho
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private LoginService loginService;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		LoginLdapDto loginLdap = new LoginLdapDto(username, password);

		try {
			UsuarioLdapDto usuarioLogado = loginService.loginLdap(loginLdap);
			if (usuarioLogado != null) {
				Optional<Usuario> usuarioPresenteNoSistema = usuarioService
						.findByMatricula(usuarioLogado.getMatricula());
				if (usuarioPresenteNoSistema.isPresent()) {
					List<SimpleGrantedAuthority> permissoes = extrairPerfisEPermissoesDeUsuario(
							usuarioPresenteNoSistema.get().getPerfil());
					return new UsernamePasswordAuthenticationToken(usuarioLogado, null, permissoes);
				} else {
					/**
					 * Usuário não cadastrado no sistema
					 */
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private List<SimpleGrantedAuthority> extrairPerfisEPermissoesDeUsuario(Perfil perfil) {
		List<SimpleGrantedAuthority> perfilPermissoes = new ArrayList<SimpleGrantedAuthority>();
		perfilPermissoes.add(new SimpleGrantedAuthority("ROLE_" + perfil.getNome()));
		for (PerfilPermissao permissao : perfil.getPerfilPermissao()) {
			if (permissao.isTemPermissao())
				perfilPermissoes.add(new SimpleGrantedAuthority(permissao.getPermissao().getNome()));
		}
		return perfilPermissoes;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}