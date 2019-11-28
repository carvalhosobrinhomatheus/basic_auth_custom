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
@Table(name = "criterio_intervalo", schema = "pdaf")
public class CriterioIntervalo implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "inicio_intervalo")
    private int inicioIntervalo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fim_intervalo")
    private int fimIntervalo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_criterio_intervalo")
    private Long idCriterioIntervalo;
    
    
    @JoinColumn(name = "id_criterio", referencedColumnName = "id_criterio")
    @ManyToOne(optional = false)
    private Criterio criterio;

    public int getInicioIntervalo() {
        return inicioIntervalo;
    }

    public void setInicioIntervalo(int inicioIntervalo) {
        this.inicioIntervalo = inicioIntervalo;
    }

    public int getFimIntervalo() {
        return fimIntervalo;
    }

    public void setFimIntervalo(int fimIntervalo) {
        this.fimIntervalo = fimIntervalo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
