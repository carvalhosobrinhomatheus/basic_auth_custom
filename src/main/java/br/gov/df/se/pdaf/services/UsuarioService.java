package br.gov.df.se.pdaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.df.se.pdaf.dtos.UsuarioDto;
import br.gov.df.se.pdaf.entities.Perfil;
import br.gov.df.se.pdaf.entities.Usuario;
import br.gov.df.se.pdaf.exceptions.ObjectNotFoundException;
import br.gov.df.se.pdaf.repositories.PerfilPermissaoRepository;
import br.gov.df.se.pdaf.repositories.PerfilRepository;
import br.gov.df.se.pdaf.repositories.PermissaoRepository;
import br.gov.df.se.pdaf.repositories.UsuarioRepository;
import br.gov.df.se.pdaf.utils.BeanListUtils;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public List<UsuarioDto> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll(); 
		List<UsuarioDto> usuariosRetornoDto = BeanListUtils.copyProperties(usuarios);
		return usuariosRetornoDto;
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("usuário não encontrado"));
	}
	
	public Optional<Usuario> findByMatricula(String matricula) {
		return usuarioRepository.findByMatricula(matricula);
	}
	
	public Usuario inserir(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		Perfil perfil = perfilRepository.getOne(usuarioDto.getPerfil());
		BeanUtils.copyProperties(usuarioDto, usuario);
		usuario.setPerfil(perfil);
		return usuarioRepository.save(usuario);
	}
	
	public Usuario alterar(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		Perfil perfil = perfilRepository.getOne(usuarioDto.getPerfil());
		BeanUtils.copyProperties(usuarioDto, usuario);
		usuario.setPerfil(perfil);
		return usuarioRepository.save(usuario);
	}
	
	public Usuario deletar(Long idUsuario) {
		Usuario usuarioDeletar = findById(idUsuario);
		usuarioRepository.delete(usuarioDeletar);
		return usuarioDeletar;
	}
	
}