package br.gov.df.se.pdaf.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.df.se.pdaf.entities.Perfil;

/**
 * @author Matheus de Carvalho Sobrinho
 */
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	public List<Perfil> findAll();
	public Optional<Perfil> findByIdPerfil(Long id);
	
}
