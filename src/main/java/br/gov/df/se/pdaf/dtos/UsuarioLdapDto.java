package br.gov.df.se.pdaf.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
* @author Matheus de Carvalho Sobrinho
*/
public class UsuarioLdapDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String matricula;
	
	//@NotNull
	private String nome;
	
	//@NotNull
	private String token;
	
	public UsuarioLdapDto(String matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
