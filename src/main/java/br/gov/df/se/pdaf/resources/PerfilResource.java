package br.gov.df.se.pdaf.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = "/perfil")
public class PerfilResource {

	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@Autowired
	private PerfilPermissaoService perfilPermissaoService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Perfil> perfis = perfilService.findAll();
		return ResponseEntity.ok().body(perfis);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(perfilService.findById(id));
	}

	@PostMapping
	public ResponseEntity<?> inserir(@Valid @RequestBody PerfilDto perfilDto) throws Exception {
		Perfil perfilSalvo = perfilService.inserir(perfilDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(perfilSalvo.getIdPerfil()).toUri();
		HttpHeaders header = new HttpHeaders();
		header.add("id", perfilSalvo.getIdPerfil().toString());
		return ResponseEntity.created(uri).headers(header).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody PerfilDto perfilDto) throws Exception {
		perfilDto.setIdPerfil(id);
		perfilService.alterar(perfilDto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/ativarInativar/{id}")
	public ResponseEntity<?> inativar(@PathVariable Long id, @RequestBody PerfilDto perfilDto) throws Exception {
		perfilService.ativarInativar(perfilDto);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/simples")
	public ResponseEntity<List<PerfilSimplesDto>> findAllSimples() {
		return ResponseEntity.ok().body(perfilService.findAllSimples());
	}
	
	@PutMapping("/perfilPermissao/{idPerfilPermissao}")
	public ResponseEntity<?> alterar(@PathVariable Long idPerfilPermissao) {
		perfilPermissaoService.alterarPerfilPermissao(idPerfilPermissao);
		return ResponseEntity.noContent().build();
	}
	
}
