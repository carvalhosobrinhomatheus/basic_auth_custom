package br.gov.df.se.pdaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.gov.df.se.pdaf.entities.Perfil;
import br.gov.df.se.pdaf.entities.Permissao;
import br.gov.df.se.pdaf.entities.Usuario;
import br.gov.df.se.pdaf.repositories.PerfilRepository;
import br.gov.df.se.pdaf.repositories.PermissaoRepository;
import br.gov.df.se.pdaf.repositories.UsuarioRepository;

@SpringBootApplication
public class PdafApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PdafApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {}

	public void dadosDeTesteIniciais() {
		Perfil perfil = new Perfil();
		perfil.setNome("ADMIN");
		perfil.setAtivo(true);

		perfilRepository.save(perfil);

		Set<Perfil> perfis = new HashSet<Perfil>();
		perfis.add(perfil);

		Permissao permissao = new Permissao();
		permissao.setAtivo(true);
		permissao.setNome("VISUALIZAR_USUARIO");
		//permissao.setPerfil(perfis);

		permissaoRepository.save(permissao);

		Set<Permissao> permissoes = new HashSet<Permissao>();
		permissoes.add(permissao);

		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setMatricula("2444267");
		usuario.setIdUsuario(0L);
		usuario.setNome("Matheus de Carvalho Sobrinho");
		//usuario.setPerfil(perfis);
		
		usuarioRepository.save(usuario);
	}
	
}