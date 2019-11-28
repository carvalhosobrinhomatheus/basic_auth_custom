package br.gov.df.se.pdaf.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.df.se.pdaf.entities.Perfil;
import br.gov.df.se.pdaf.entities.Permissao;
import br.gov.df.se.pdaf.exceptions.ObjectNotFoundException;
import br.gov.df.se.pdaf.repositories.PerfilRepository;
import br.gov.df.se.pdaf.repositories.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;

	public List<Permissao> findAll() {
		return permissaoRepository.findAll();
	}

	public Permissao findById(Long idPermissao) {
		Optional<Permissao> obj = permissaoRepository.findById(idPermissao);
		return obj.orElseThrow(() -> new ObjectNotFoundException("permissao não encontrada"));	
	}
	
	@Transactional
	public Permissao atribuirPermissaoParaPerfil(Long idPerfil, Long idPermissao) {
		return null;	
	}
	
	public Permissao removerPermissaoParaPerfil(Long idPerfil, Long idPermissao) {
		return null;
	}
}