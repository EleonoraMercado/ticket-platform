package it.emercado.spring_ticket_platform.repository;

import it.emercado.spring_ticket_platform.model.AdminModel;
import it.emercado.spring_ticket_platform.model.OperatoreModel;

public interface GestioneServiziAdmin {

	AdminModel trovaAdminPerEmail(String email);
	
	OperatoreModel creaNuovoOperatore(String email, String password);
}
