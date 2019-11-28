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
@Table(name = "unidade_executora", schema = "pdaf")
public class UnidadeExecutora implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidade_executora")
    private Long idUnidadeExecutora;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome")
    private String nome;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private Character tipo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnpj")
    private long cnpj;
    
    /**
     * Observar que Unidade Executora REGIONAL não possui código inep
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_inep")
    private long codigoInep;
    
    /**
     * Deverá ser feita consulta no sigep
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_unidade_administrativa")
    private String codigoUnidadeAdministrativa;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeExecutora")
    private List<UnidadeCriterio> unidadeCriterio;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeExecutora")
    private List<UnidadeDadosAnoExecucao> unidadeDadosAnoExecucao;

}
