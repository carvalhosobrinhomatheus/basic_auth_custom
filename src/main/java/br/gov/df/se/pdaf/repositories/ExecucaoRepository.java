package br.gov.df.se.pdaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.df.se.pdaf.entities.Execucao;

/**
* @author Matheus de Carvalho Sobrinho
*/
@Repository
public interface ExecucaoRepository extends JpaRepository<Execucao, Long>{

}
