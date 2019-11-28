package br.gov.df.se.pdaf.dtos;

import java.io.Serializable;

/**
* @author Matheus de Carvalho Sobrinho
*/
public class CredentialsDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String email) {
		this.username = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String senha) {
		this.password = senha;
	}
}
