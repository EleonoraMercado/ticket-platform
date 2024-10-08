package it.emercado.spring_ticket_platform.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import it.emercado.spring_ticket_platform.model.User;

public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> utente = userRepository.findByEmail(email);
		
		if(utente.isPresent()) {
			return new DatabaseUserDetails(utente.get());
		} else {
			throw new UsernameNotFoundException("Utente non trovato");
		}
		
		
	}

}
