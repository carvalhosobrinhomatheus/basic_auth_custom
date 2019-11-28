package br.gov.df.se.pdaf.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "banco", schema = "pdaf")
public class Banco implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_banco")
    private Long codigoBanco;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome_banco")
    private String nomeBanco;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_bancario")
    private Long codigoBancario;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "sigla")
    private String sigla;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    private List<UnidadeDadosAnoExecucao> unidadeDadosAnoExecucao;
    
}
