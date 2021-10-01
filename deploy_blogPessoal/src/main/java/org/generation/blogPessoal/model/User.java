package org.generation.blogPessoal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarios")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O atributo Nome é obrigatório.")
	private String nome;

	@NotBlank(message = "O atributo Username é obrigatório.")
	@Size(max = 25, message = "O username deve ter no máximo 25 caracteres.")
	@Column(unique = true)
	private String username;

	@NotBlank(message = "O atributo Email é obrigatório.")
	@Size(max = 50, message = "O email deve ter no máximo 50 caracteres.")
	@Column(unique = true)
	private String email;

	@NotBlank(message = "O atributo Senha é obrigatória.")
	@Size(min = 8, message = "A senha deve ter no mínimo 6 caracteres.")
	private String senha;

	@NotNull(message = "O atributo Data de Nascimento é obrigatório e o formato deve ser 'yyyy-MM-dd'.")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataNascimento;

	private String fotoPerfil;

	private String fotoCapa;

	private String admin;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("user")
	private List<Postagem> postagem;

	public User(long id, String nome, String username, String senha, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.username = username;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	public User() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getFotoCapa() {
		return fotoCapa;
	}

	public void setFotoCapa(String fotoCapa) {
		this.fotoCapa = fotoCapa;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
