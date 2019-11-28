/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.se.pdaf.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "execucao", schema = "pdaf")
public class Execucao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_execucao")
    private Long idExecucao;
    
    @Column(name = "data_liberacao")
    @Temporal(TemporalType.DATE)
    private Date dataLiberacao;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private Double valor;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "semestre")
    private Character semestre;
    
    @JoinColumn(name = "id_status", referencedColumnName = "id_status")
    @ManyToOne(optional = false)
    private ExecucaoStatus execucaoStatus;
    
    @JoinColumn(name = "id_unidade_criterio", referencedColumnName = "id_unidade_criterio")
    @ManyToOne(optional = false)
    private UnidadeCriterio unidadeCriterio;
    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    
}
