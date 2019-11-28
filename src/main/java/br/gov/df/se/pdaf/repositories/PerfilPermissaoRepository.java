package br.gov.df.se.pdaf.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.df.se.pdaf.entities.Perfil;
import br.gov.df.se.pdaf.entities.PerfilPermissao;
import br.gov.df.se.pdaf.entities.Permissao;

/**
 * @author Matheus de Carvalho Sobrinho
 */
@Repository
public interface PerfilPermissaoRepository extends JpaRepository<PerfilPermissao, Long> {
	
	public List<PerfilPermissao> findAll();
	public Optional<PerfilPermissao> findByPermissaoAndPerfil(Permissao permissao, Perfil perfil);
	
}
