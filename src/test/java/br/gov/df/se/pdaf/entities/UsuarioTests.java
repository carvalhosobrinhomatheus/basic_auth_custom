package br.gov.df.se.pdaf.entities;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.df.se.pdaf.services.UsuarioService;

// https://static.javadoc.io/org.mockito/mockito-core/2.2.9/org/mockito/Mockito.html
// http://www.codeatest.com/mockito-isolamento-testes-unidade/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTests {

	private String MOCK_MATRICULA = "2444267";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	public void findByEmail() {
		assertNotNull(usuarioService.findByMatricula(MOCK_MATRICULA));
	}

}