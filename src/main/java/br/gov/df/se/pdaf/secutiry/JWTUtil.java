package br.gov.df.se.pdaf.secutiry;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
* @author Matheus de Carvalho Sobrinho
*/
@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;
	
	public JWTUtil() {};
 
	public String generateToken(String username, String perfis) {
		Claims claims = Jwts.claims().setSubject(username);
		
        claims.put("permissao", perfis.substring(1, perfis.length()-1).replace(" ", ""));
        
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public boolean validateToken(String token) {
		Claims claims = getClaims(token);
		
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
	public String[] getPermissoes(String token) {
		Claims claims = getClaims(token);
		String permissoes = (String) claims.get("permissao");
		String arrayDePerfisEPermissoes[] = permissoes.split(",");
		return arrayDePerfisEPermissoes;
	}
	
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

}
