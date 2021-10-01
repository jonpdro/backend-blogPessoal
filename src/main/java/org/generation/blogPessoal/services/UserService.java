package org.generation.blogPessoal.services;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.User;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> listarUsuarios() {
		return repository.findAll();

	}

	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> usuario = repository.findByUsernameIgnoreCase(user.get().getUsername());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getUsername() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setUsername(usuario.get().getUsername());
				user.get().setEmail(usuario.get().getEmail());
				user.get().setAdmin(usuario.get().getAdmin());
				user.get().setFotoPerfil(usuario.get().getFotoPerfil());
				user.get().setFotoCapa(usuario.get().getFotoCapa());
				user.get().setDataNascimento(usuario.get().getDataNascimento());

				return user;
			}
		}
		return null;
	}

	public Optional<User> Cadastrar(User usuario) {
		Optional<User> user = repository.findByUsernameIgnoreCase(usuario.getUsername());

		if (user.isPresent()) {
			return Optional.ofNullable(null);
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return Optional.of(repository.save(usuario));
	}

	public Optional<User> atualizarUsuario(User usuario) {
		if (repository.findById(usuario.getId()).isPresent()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);

			return Optional.of(repository.save(usuario));

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
		}
	}

}
