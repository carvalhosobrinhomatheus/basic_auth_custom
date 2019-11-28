/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.se.pdaf.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
*
* @author Matheus de Carvalho Sobrinho
*/
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "perfil_permissao", schema = "pdaf")
public class PerfilPermissao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerfilPermissao;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    private Perfil perfil;
    
	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    private Permissao permissao;
	
	private boolean temPermissao;

	public Long getIdPerfilPermissao() {
		return idPerfilPermissao;
	}

	public void setIdPerfilPermissao(Long idPerfilPermissao) {
		this.idPerfilPermissao = idPerfilPermissao;
	}

	public boolean isTemPermissao() {
		return temPermissao;
	}

	public void setTemPermissao(boolean temPermissao) {
		this.temPermissao = temPermissao;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	
}
