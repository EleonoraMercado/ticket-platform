package it.emercado.spring_ticket_platform.security;

import java.util.HashSet;
import java.util.Set;

import it.emercado.spring_ticket_platform.model.User;
import it.emercado.spring_ticket_platform.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails {

	private final Integer id;
	private final String email;
	private final String password;
	private final Set<GrantedAuthority> authorities;
	
	public DatabaseUserDetails(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.authorities = new HashSet<>();
		
		for(Role ruolo : user.getRoles()) {
			this.authorities.add(new SimpleGrantedAuthority(ruolo.getName()));
		}
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	

}
