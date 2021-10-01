package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.generation.blogPessoal.model.User;
import org.generation.blogPessoal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private User usuario;
	private User usuarioUpdate;
	private User usuarioAdmin;

	@Autowired
	private UserRepository repository;

	@BeforeEach
	public void start() throws ParseException {
		LocalDate data = LocalDate.parse("2003-01-27", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		usuarioAdmin = new User(0L, "Administrador", "admin", "123456", data);

		if (!repository.findByUsernameIgnoreCase(usuarioAdmin.getUsername()).isPresent()) {			
			 HttpEntity<User> request = new HttpEntity<User>(usuarioAdmin);
				testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, User.class);
		}

		LocalDate dataPost = LocalDate.parse("2000-01-29", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		usuario = new User(0L, "João Pedro Cruz", "jotinha", "123456", dataPost);

		LocalDate dataPut = LocalDate.parse("2000-02-06", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		usuarioUpdate = new User(2L, "João Pedro Cruz Gomes", "jotinha1", "1234567", dataPut);

	}

	@Test
	@Order(1)
	@DisplayName("✔ Cadastrar Usuário!")
	void deveRealizarPostUsuario() {
		HttpEntity<User> request = new HttpEntity<User>(usuario);

		ResponseEntity<User> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request,
				User.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}

	@Test
	@Order(2)
	@DisplayName("👍 Listar todos os Usuários!")
	public void deveMostrarTodosUsuarios() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("admin", "123456").exchange("/usuarios/all",
				HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@Order(3)
	@DisplayName("😳 Alterar Usuário!")
	public void deveRealizarPutUsuario() {
		HttpEntity<User> request = new HttpEntity<User>(usuarioUpdate);

		ResponseEntity<User> resposta = testRestTemplate.withBasicAuth("admin", "123456")
				.exchange("/usuarios/atualizar", HttpMethod.PUT, request, User.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

}
