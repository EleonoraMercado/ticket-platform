package it.emercado.spring_ticket_platform.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.emercado.spring_ticket_platform.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
}
