package br.gov.df.se.pdaf.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.df.se.pdaf.dtos.PerfilDto;
import br.gov.df.se.pdaf.dtos.PerfilSimplesDto;
import br.gov.df.se.pdaf.entities.Perfil;
import br.gov.df.se.pdaf.entities.PerfilPermissao;
import br.gov.df.se.pdaf.entities.Permissao;
import br.gov.df.se.pdaf.exceptions.ObjectNotFoundException;
import br.gov.df.se.pdaf.repositories.PerfilPermissaoRepository;
import br.gov.df.se.pdaf.repositories.PerfilRepository;
import br.gov.df.se.pdaf.repositories.PermissaoRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private PermissaoService permissaoService;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PerfilPermissaoRepository perfilPermissaoRepository;

	public List<Perfil> perfisRetorno;

	public List<Perfil> findAll() {
		return perfilRepository.findAll();
	}

	public List<PerfilSimplesDto> findAllSimples() {
		List<Perfil> perfisCadastrados = perfilRepository.findAll();
		List<PerfilSimplesDto> perfisSimplificados = new ArrayList<>();
		for (Perfil perfil : perfisCadastrados) {
			if (perfil.getAtivo()) {
				PerfilSimplesDto perfilSimplificado = new PerfilSimplesDto(perfil.getIdPerfil(), perfil.getNome());
				perfisSimplificados.add(perfilSimplificado);
			}
		}
		return perfisSimplificados;
	}

	public Perfil findById(Long id) {
		Optional<Perfil> obj = perfilRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("perfil não encontrado"));
	}

	@Transactional(rollbackFor = Exception.class)
	public Perfil inserir(PerfilDto perfilDto) {
		Perfil perfil = new Perfil();
		BeanUtils.copyProperties(perfilDto, perfil);

		
		List<Permissao> permissoes = permissaoRepository.findAll();
		Set<PerfilPermissao> perfisPermissoes = new HashSet<PerfilPermissao>();
		for(Permissao permissao: permissoes) {
			PerfilPermissao perfilPermissao = new PerfilPermissao();
			perfilPermissao.setPerfil(perfil);
			perfilPermissao.setTemPermissao(false);
			perfilPermissao.setPermissao(permissao);
			PerfilPermissao perfilPermissaoSalvo = perfilPermissaoRepository.save(perfilPermissao);
			perfisPermissoes.add(perfilPermissaoSalvo);
		}
		
		perfil.setPerfilPermissao(perfisPermissoes);
		Perfil perfilSalvo = perfilRepository.save(perfil);
		
		return perfilSalvo;
	}

	public Perfil alterar(PerfilDto perfilDto) {
		Perfil perfil = new Perfil();
		BeanUtils.copyProperties(perfilDto, perfil);
		return perfilRepository.save(perfil);
	}

	public Perfil ativarInativar(PerfilDto perfilDto) {
		Perfil perfilInativar = findById(perfilDto.getIdPerfil());
		perfilInativar.setAtivo(!perfilInativar.getAtivo());
		return perfilRepository.save(perfilInativar);
	}

}