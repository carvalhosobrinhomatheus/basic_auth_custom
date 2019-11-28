package br.gov.df.se.pdaf.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.gov.df.se.pdaf.dtos.UsuarioDto;
import br.gov.df.se.pdaf.entities.Usuario;

public class BeanListUtils {

	public BeanListUtils() {
	}

	public static List<UsuarioDto> copyProperties(List<Usuario> usuarios) {
		List<UsuarioDto> usuariosRetornoDto = new ArrayList<UsuarioDto>();
		UsuarioDto usuarioDto = null;
		for (Usuario usuario : usuarios) {
			usuarioDto = new UsuarioDto();
			BeanUtils.copyProperties(usuario, usuarioDto);
			usuarioDto.setPerfil(usuario.getPerfil().getIdPerfil());
			usuariosRetornoDto.add(usuarioDto);
		}

		return usuariosRetornoDto;
	}
}