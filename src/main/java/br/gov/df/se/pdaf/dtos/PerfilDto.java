/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.se.pdaf.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.gov.df.se.pdaf.entities.Perfil;

/**
*
* @author Matheus de Carvalho Sobrinho
*/
public class PerfilDto {

	private static final long serialVersionUID = 1L;
	
	private Long idPerfil;

    private Boolean ativo;
    
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String nome;
    
    PerfilDto(){}
    
    public PerfilDto(Long idPerfil, Boolean ativo, String nome){
    	this.idPerfil = idPerfil;
    	this.ativo = ativo;
    	this.nome = nome;
    }
    
    public PerfilDto(Perfil perfil){
    	this.idPerfil = perfil.getIdPerfil();
    	this.ativo = perfil.getAtivo();
    	this.nome = perfil.getNome();
    }

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public boolean isVisivel() {
		return ativo;
	}

	public void setVisivel(boolean visivel) {
		this.ativo = visivel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    
}
