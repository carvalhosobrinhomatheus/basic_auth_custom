package br.gov.df.se.pdaf.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.df.se.pdaf.entities.PerfilPermissao;
import br.gov.df.se.pdaf.services.PerfilPermissaoService;

/**
 * @author Matheus de Carvalho Sobrinho
 */

@RestController
@RequestMapping(value = "/perfilpermissao")
public class PerfilPermissaoResource {
	
	@Autowired
	private PerfilPermissaoService perfilPermissaoService;
	
	@PutMapping("/{idPerfilPermissao}")
	public ResponseEntity<?> alterar(@PathVariable Long idPerfilPermissao, @RequestBody PerfilPermissao perfilPermissao) {
		perfilPermissaoService.alterarPerfilPermissao(idPerfilPermissao);
		return ResponseEntity.noContent().build();
	}

}
