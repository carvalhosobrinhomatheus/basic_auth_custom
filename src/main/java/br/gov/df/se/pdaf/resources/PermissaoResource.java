package br.gov.df.se.pdaf.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.df.se.pdaf.dtos.PerfilDto;
import br.gov.df.se.pdaf.dtos.PerfilSimplesDto;
import br.gov.df.se.pdaf.entities.Perfil;
import br.gov.df.se.pdaf.entities.Permissao;
import br.gov.df.se.pdaf.services.PerfilPermissaoService;
import br.gov.df.se.pdaf.services.PerfilService;
import br.gov.df.se.pdaf.services.PermissaoService;

/**
 * @author Matheus de Carvalho Sobrinho
 */
@RestController
@RequestMapping(value = "/permissao")
public class PermissaoResource {

	@Autowired
	private PermissaoService permissaoService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(permissaoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(permissaoService.findById(id));
	}

}
