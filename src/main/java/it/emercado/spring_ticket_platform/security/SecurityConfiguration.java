package it.emercado.spring_ticket_platform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
		http
		
			.authorizeHttpRequests()
			
			// Rotte per gli admin
	        .requestMatchers(HttpMethod.GET, "/admin/dashboardAdmin", 
	        		"/admin/tickets", 
	        		"/admin/ticket/vedi/{id}", 
	        		"/admin/ticket/modifica/{id}", 
	        		"/admin/ticketDettaglio/{id}", 
	        		"/admin/creaCategoria", 
	        		"/admin/categorie/crea", 
	        		"/admin/operatori", 
	        		"/admin/listaNote", 
	        		"/admin/ticket/creaTicket").hasAuthority("admin")
	        .requestMatchers(HttpMethod.POST,"/admin/salvaOperatore",
	        		"/admin/ticket/{id}/inviaModifica",
	        		"/admin/ticket/{id}/aggiungiNota",
	        		"/admin/ticket/salvaTicket").hasAuthority("admin")
	        
	        // Rotte per gli operatori
	        .requestMatchers(HttpMethod.GET,
	                "/operatore/dashboardOperatore",
	                "/operatore/operatoreTicketList",
	                "/operatore/ticket/vedi/*",
	                "/operatore/ticket/*/nuovaNota",
	                "/operatore/ticket/*/note",
	                "/operatore/nota/elimina/*",
	                "/operatore/profilo",
	                "/operatore/profilo/edit",
	                "/operatore/ticket/*/edit"
	            ).hasAuthority("operatore")
	            .requestMatchers(HttpMethod.POST,
	                "/operatore/ticket/*/aggiornaStato",
	                "/operatore/ticket/*/salvaNota",
	                "/operatore/nota/modifica",
	                "/operatore/profilo/update",
	                "/operatore/ticket/*/save"
	            ).hasAuthority("operatore")
	        
	        .anyRequest().authenticated() 
	        .and()
			.formLogin(formLogin -> formLogin
				.usernameParameter("email")
	        	.successHandler(gestoreSuccessoAutenticazione()) 
	        	.permitAll() 
	    	)
	    	.logout(logout -> logout
	        	.logoutSuccessUrl("/login?logout") // Dopo il logout, reindirizza alla pagina di login
	        	.permitAll()
	    	);
			
		return http.build();
			
			
	  } 
	
	  @Bean
	  AuthenticationSuccessHandler gestoreSuccessoAutenticazione() {
	      return new GestoreSuccessoAutenticazione(); // Usa il gestore creato da me
	  }
	  
	  @Bean
	  DatabaseUserDetailsService userDetaisService() {
		  return new DatabaseUserDetailsService();
	  }
	  
	  @Bean
	  PasswordEncoder passwordEncoder() {
		  return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	  }
	  
	  //questo mi servira per dire a spring in che modo deve eseguire
	  //i passaggi step dopo step, filtro dopo filtro, per autenticare un utente
	  @Bean
	  DaoAuthenticationProvider authenticationProvider() {
		  DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		  
		  authProvider.setUserDetailsService(userDetaisService());
		  authProvider.setPasswordEncoder(passwordEncoder());
		  
		  return authProvider;
	  }
        
}
