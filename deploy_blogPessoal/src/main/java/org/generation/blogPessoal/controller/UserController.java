package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.User;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(service.listarUsuarios());
	}

	@PostMapping("/entrar")
	public ResponseEntity<UserLogin> logar(@RequestBody Optional<UserLogin> usuario) {
		return service.Logar(usuario).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<User> cadastro(@RequestBody User usuario) {
		return service.Cadastrar(usuario).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
	}

	@PutMapping("/atualizar")
	public ResponseEntity<User> put(@RequestBody User usuario) {
		return service.atualizarUsuario(usuario).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

}
