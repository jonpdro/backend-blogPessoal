package org.generation.blogPessoal.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTest {

	public User usuario;
	public User usuarioNulo = new User();

	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	Validator validator = factory.getValidator();

	@BeforeEach
	public void start() {
		LocalDate data = LocalDate.parse("2003-01-27", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		usuario = new User(0L, "João Pedro Cruz", "jotaskrt", "123456", data);
	}

	@Test
	@DisplayName("✔ Valida Atributos Não Nulos")
	void testValidaAtributos() {
		Set<ConstraintViolation<User>> violacao = validator.validate(usuario);

		System.out.println(violacao.toString());

		assertTrue(violacao.isEmpty());
	}

	@Test
	@DisplayName("✖ Não Valida Atributos Nulos")
	void testNaoValidaAtributos() {
		Set<ConstraintViolation<User>> violacao = validator.validate(usuarioNulo);

		System.out.println(violacao.toString());

		assertTrue(violacao.isEmpty());
	}

}
