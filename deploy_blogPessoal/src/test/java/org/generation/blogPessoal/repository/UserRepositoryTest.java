package org.generation.blogPessoal.repository;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.generation.blogPessoal.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class UserRepositoryTest {

	@Autowired
	private UserRepository usuarioRepository;

	@BeforeAll
	void start() {

		LocalDate data = LocalDate.parse("2003-01-27", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		User usuario = new User(0, "João Pedro da Cruz", "jotaskrt", "123456", data);
		if (!usuarioRepository.findByUsernameIgnoreCase(usuario.getUsername()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new User(0, "Nayza Matos Cruz", "nayza", "123456", data);
		if (!usuarioRepository.findByUsernameIgnoreCase(usuario.getUsername()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new User(0, "Ana Beatriz Yujra Matos", "biaespejo", "123456", data);
		if (!usuarioRepository.findByUsernameIgnoreCase(usuario.getUsername()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new User(0, "Giovanna Rosimira Cruz", "gigica", "123456", data);
		if (!usuarioRepository.findByUsernameIgnoreCase(usuario.getUsername()).isPresent())
			usuarioRepository.save(usuario);
	}

	@Test
	@DisplayName("💾 Retorna o nome")
	public void findByNomeRetornaNome() {
		User usuario = usuarioRepository.findByNome("João Pedro da Cruz");
		assertTrue(usuario.getNome().equals("João Pedro da Cruz"));
	}

//	@Test
//	@DisplayName("💾 Retorna 3 usuarios")
//	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {
//		List<User> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Cruz");
//		assertEquals(3, listaDeUsuarios.size());
//	}

	@AfterAll
	public void end() {
		System.out.println("Teste finalizado!");
	}

}
