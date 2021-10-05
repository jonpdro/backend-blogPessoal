package org.generation.blogPessoal.security;

import java.util.Optional;

import org.generation.blogPessoal.model.User;
import org.generation.blogPessoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> usuario = userRepository.findByUsernameIgnoreCase(username);
		usuario.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

		return usuario.map(UserDetailsImpl::new).get();
	}

}