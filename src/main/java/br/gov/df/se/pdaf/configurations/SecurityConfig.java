package br.gov.df.se.pdaf.configurations;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.gov.df.se.pdaf.secutiry.CustomAuthenticationProvider;
import br.gov.df.se.pdaf.secutiry.JWTAuthenticationFilter;
import br.gov.df.se.pdaf.secutiry.JWTAuthorizationFilter;
import br.gov.df.se.pdaf.secutiry.JWTUtil;

/**
 * @author Matheus de Carvalho Sobrinho
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTUtil jwtUtil;

	private static final String[] PUBLIC_MATCHERS = {};

	private static final String[] PUBLIC_MATCHERS_GET = {};

	private static final String[] PUBLIC_MATCHERS_POST = {};

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/**
		 * Para o Spring Security, se no perfil houver um prefixo "ROLE_", então é
		 * entendido com um perfil no sistema, Caso não hava esse prefixo, é visto como
		 * uma autorização (permissão) que o usuário deve ter no sistema, que no caso do
		 * pdaf é dependente do perfil;
		 */

		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
				.antMatchers(HttpMethod.GET, "/usuario/**").hasAnyAuthority("VISUALIZAR_USUARIO")
				.antMatchers(HttpMethod.POST, "/usuario/**").hasAnyAuthority("INSERIR_USUARIO")
				.antMatchers(HttpMethod.PUT, "/usuario/**").hasAnyAuthority("ALTERAR_USUARIO")
				.antMatchers(HttpMethod.DELETE, "/usuario/**").hasAnyAuthority("DELETAR_USUARIO")

				.antMatchers(HttpMethod.GET, "/perfil/**").hasAnyAuthority("VISUALIZAR_PERFIL")
				.antMatchers(HttpMethod.POST, "/perfil/**").hasAnyAuthority("INSERIR_PERFIL")
				.antMatchers(HttpMethod.PUT, "/perfil/**").hasAnyAuthority("ALTERAR_PERFIL")
				.antMatchers(HttpMethod.DELETE, "/perfil/**").hasAnyAuthority("DELETAR_PERFIL")
				
				//.antMatchers(HttpMethod.GET, "/perfilpermissao/**").hasAnyAuthority("VISUALIZAR_PERFIL")
				//.antMatchers(HttpMethod.POST, "/perfilpermissao/**").hasAnyAuthority("INSERIR_PERFIL")
				.antMatchers(HttpMethod.PUT, "/perfilpermissao/**").hasAnyAuthority("ALTERAR_PERFIL")
				//.antMatchers(HttpMethod.DELETE, "/permperfilpermissaoissao/**").hasAnyAuthority("DELETAR_PERFIL")
				
				//.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().antMatchers(PUBLIC_MATCHERS).permitAll()
				.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		
		configuration.addAllowedMethod(HttpMethod.PUT);
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofMillis(10000)).setReadTimeout(Duration.ofMillis(10000)).build();
	}

}