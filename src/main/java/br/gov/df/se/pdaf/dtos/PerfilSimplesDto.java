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
public class PerfilSimplesDto {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long idPerfil;
    
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String nome;
    
    PerfilSimplesDto(){}
    
    public PerfilSimplesDto(Long idPerfil, String nome){
    	this.idPerfil = idPerfil;
    	this.nome = nome;
    }
    
    public PerfilSimplesDto(Perfil perfil){
    	this.idPerfil = perfil.getIdPerfil();
    	this.nome = perfil.getNome();
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
    
}
