package br.gov.df.se.pdaf.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
* @author Matheus de Carvalho Sobrinho
*/
public class UsuarioDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idUsuario;
	
	@NotNull(message = "matricula não pode ser nulo")
	private String matricula;
	
	@NotNull(message = "nome não pode ser nulo")
	private String nome;
	
	private Boolean ativo;
	
	@NotNull(message = "perfil não pode ser nulo")
	private Long perfil;
	
	public UsuarioDto(){}
	
	public UsuarioDto(Long idUsuario, String matricula, String nome, Boolean ativo, Long perfil){
		this.idUsuario = idUsuario;
		this.matricula = matricula;
		this.nome = nome;
		this.ativo = ativo;
		this.perfil = perfil;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getPerfil() {
		return perfil;
	}

	public void setPerfil(Long perfil) {
		this.perfil = perfil;
	}

}
