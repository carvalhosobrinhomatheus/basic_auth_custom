package br.gov.df.se.pdaf.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.df.se.pdaf.dtos.UsuarioDto;
import br.gov.df.se.pdaf.entities.Usuario;
import br.gov.df.se.pdaf.services.PerfilPermissaoService;
import br.gov.df.se.pdaf.services.UsuarioService;

/**
 * @author Matheus de Carvalho Sobrinho
 */
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(usuarioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(usuarioService.findById(id));
	}

	@PostMapping
	public ResponseEntity<?> inserir(@Valid @RequestBody UsuarioDto usuarioDto) throws Exception {
		Usuario usuarioSalvo = usuarioService.inserir(usuarioDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usuarioSalvo.getIdUsuario()).toUri();
		
		HttpHeaders header = new HttpHeaders();
		header.add("id", usuarioSalvo.getIdUsuario().toString());
		return ResponseEntity.created(uri).headers(header).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto) throws Exception {
		usuarioDto.setIdUsuario(id);
		usuarioService.alterar(usuarioDto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) throws Exception {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
