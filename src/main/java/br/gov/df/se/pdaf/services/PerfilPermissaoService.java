package br.gov.df.se.pdaf.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.df.se.pdaf.entities.Perfil;
import br.gov.df.se.pdaf.entities.PerfilPermissao;
import br.gov.df.se.pdaf.entities.Permissao;
import br.gov.df.se.pdaf.exceptions.ObjectNotFoundException;
import br.gov.df.se.pdaf.repositories.PerfilPermissaoRepository;
import br.gov.df.se.pdaf.repositories.PerfilRepository;
import br.gov.df.se.pdaf.repositories.PermissaoRepository;

@Service
public class PerfilPermissaoService {

	@Autowired
	private PerfilPermissaoRepository perfilPermissaoRepository;

	public List<PerfilPermissao> findAll() {
		return perfilPermissaoRepository.findAll();
	}
	
	public PerfilPermissao findByPermissaoAndPerfil(Long idPermissao, Long idPerfil) {
		Perfil perfil = new Perfil();
		perfil.setIdPerfil(idPerfil);
		Permissao permissao = new Permissao();
		permissao.setIdPermissao(idPermissao);
		Optional<PerfilPermissao> perfilPermissao = perfilPermissaoRepository.findByPermissaoAndPerfil(permissao, perfil);
		return perfilPermissao.orElseThrow(() -> new ObjectNotFoundException("perfilPermissao não encontrado"));	
	}
	
	public PerfilPermissao alterarPerfilPermissao(Long idPerfilPermissao) {
		PerfilPermissao perfilPermissao = perfilPermissaoRepository.getOne(idPerfilPermissao);
		perfilPermissao.setTemPermissao(!perfilPermissao.isTemPermissao());
		return perfilPermissaoRepository.save(perfilPermissao);
	}
	
}