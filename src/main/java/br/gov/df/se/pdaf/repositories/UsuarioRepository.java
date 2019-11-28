package br.gov.df.se.pdaf.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.df.se.pdaf.entities.Usuario;

/**
* @author Matheus de Carvalho Sobrinho
*/
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public List<Usuario> findAll();
	public Optional<Usuario> findByIdUsuario(Long id);
	public Optional<Usuario> findByMatricula(String matricula);
	public Usuario save(Usuario usuario);
	public void delete(Usuario usuario);
	
}
