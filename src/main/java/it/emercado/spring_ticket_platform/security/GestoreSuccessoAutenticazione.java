package it.emercado.spring_ticket_platform.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GestoreSuccessoAutenticazione implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		 // Controllo i ruoli dell'utente e li reindirizzo alla dashboard specifica di ciascuno
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
            response.sendRedirect("/admin/dashboardAdmin");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("operatore"))) {
            response.sendRedirect("/operatore/dashboardOperatore");
        } else {
            response.sendRedirect("/login?error"); // In caso di errore
        }

	}

}
