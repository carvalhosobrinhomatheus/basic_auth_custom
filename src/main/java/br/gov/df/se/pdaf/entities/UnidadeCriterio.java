/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.se.pdaf.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
*
* @author Matheus de Carvalho Sobrinho
* @author Vinícius Orrú
*/
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "unidade_criterio", schema = "pdaf")
public class UnidadeCriterio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidade_criterio")
    private Long idUnidadeCriterio;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ano_execucao")
    private String anoExecucao;
    
    @Column(name = "qtd_base_criterio")
    private Long qtdBaseCriterio;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeCriterio")
    private List<Execucao> execucao;
    
    @JoinColumn(name = "id_criterio", referencedColumnName = "id_criterio")
    @ManyToOne(optional = false)
    private Criterio criterio;

    @JoinColumn(name = "id_unidade_executora", referencedColumnName = "id_unidade_executora")
    @ManyToOne(optional = false)
    private UnidadeExecutora unidadeExecutora;

}
