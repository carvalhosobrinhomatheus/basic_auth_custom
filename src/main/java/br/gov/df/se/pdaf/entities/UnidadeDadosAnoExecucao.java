/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.se.pdaf.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "unidade_dados_ano_execucao", schema = "pdaf")
public class UnidadeDadosAnoExecucao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidade_dados_ano_execucao")
    private Long idUnidadeDadosAnoExecucao;//TODO: mudar para id_unidade_dados_ano_execucao no banco de dados
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "digito_conta")
    private Character digitoConta;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "conta")
    private Integer conta;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "digito_agencia")
    private Character digitoAgencia;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "agencia")
    private Integer agencia;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "terceirizado")
    private Boolean terceirizado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_base")
    private Integer qtdBase;
    
    @JoinColumn(name = "codigo_banco", referencedColumnName = "codigo_banco")
    @ManyToOne(optional = false)
    private Banco banco;
    
    @JoinColumn(name = "id_unidade_executora", referencedColumnName = "id_unidade_executora")
    @ManyToOne(optional = false)
    private UnidadeExecutora unidadeExecutora;
    
}
