/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.se.pdaf.dtos;

/**
*
* @author Matheus de Carvalho Sobrinho
*/
public class PerfilGetDto {

	private static final long serialVersionUID = 1L;
	
	private Long idPerfil;

    private Boolean ativo;
    
    private String nome;
    
    PerfilGetDto(){}
    
    public PerfilGetDto(Long idPerfil, Boolean ativo, String nome){
    	this.idPerfil = idPerfil;
    	this.ativo = ativo;
    	this.nome = nome;
    }
    
	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
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
	
}
