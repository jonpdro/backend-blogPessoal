package org.generation.blogPessoal.repository;

// import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUsernameIgnoreCase(String username);

	public Optional<User> findAllByNomeContainingIgnoreCase(String nome);

	// JUnity
	// public List<User> findAllByNomeContainingIgnoreCase(String nome);

	// JUnity
	public User findByNome(String nome);
}
