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
@Table(name = "criterio", schema = "pdaf")
public class Criterio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_criterio")
    private Long idCriterio;
    
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
    @Column(name = "incidencia")
    private Character incidencia;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private Boolean ativo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "criterio")
    private List<UnidadeCriterio> unidadeCriterio;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "criterio")
    private List<CriterioIntervalo> criterioIntervalo;
    
}
