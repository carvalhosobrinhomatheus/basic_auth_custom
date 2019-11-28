package br.gov.df.se.pdaf.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
* @author Matheus de Carvalho Sobrinho
*/
public class LoginLdapDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String matricula;
	
	@NotNull
	private String password;
	
	public LoginLdapDto(String matricula, String password) {
		this.matricula = matricula;
		this.password = password;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
