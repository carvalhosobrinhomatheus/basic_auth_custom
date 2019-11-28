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
public class PerfilPermissaoDto {

	private static final long serialVersionUID = 1L;
	
	private Long idPermissao;

    private Boolean temPermissao;
        
    private String nome;
    private String entidadeSistema;
    
    PerfilPermissaoDto(){}
    
    public PerfilPermissaoDto(Long idPermissao, String nome, Boolean temPermissao, String entidadeSistema){
    	this.idPermissao = idPermissao;
    	this.nome = nome;
    	this.temPermissao = temPermissao;
    	this.entidadeSistema = entidadeSistema;
    }

	public Long getIdPermissao() {
		return idPermissao;
	}

	public void setIdPermissao(Long idPermissao) {
		this.idPermissao = idPermissao;
	}

	public Boolean getTemPermissao() {
		return temPermissao;
	}

	public void setTemPermissao(Boolean temPermissao) {
		this.temPermissao = temPermissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEntidadeSistema() {
		return entidadeSistema;
	}

	public void setEntidadeSistema(String entidadeSistema) {
		this.entidadeSistema = entidadeSistema;
	}
    
}
