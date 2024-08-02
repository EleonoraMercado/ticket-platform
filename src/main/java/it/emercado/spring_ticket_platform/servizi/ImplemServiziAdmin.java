package it.emercado.spring_ticket_platform.servizi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.emercado.spring_ticket_platform.model.AdminModel;
import it.emercado.spring_ticket_platform.model.OperatoreModel;
import it.emercado.spring_ticket_platform.repository.AdminRepository;
import it.emercado.spring_ticket_platform.repository.GestioneServiziAdmin;
import it.emercado.spring_ticket_platform.repository.OperatoreRepository;

@Service
public class ImplemServiziAdmin implements GestioneServiziAdmin {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private OperatoreRepository operatoreRepository;
	
	@Override
	public AdminModel trovaAdminPerEmail(String email) {
		return adminRepository.findByEmail(email);
	}
	
	@Override
	public OperatoreModel creaNuovoOperatore(String email, String password) {
		OperatoreModel nuovoOperatore = new OperatoreModel();
		nuovoOperatore.setEmail(email);
		nuovoOperatore.setPassword(password);
		
		return operatoreRepository.save(nuovoOperatore);
	}
	
	public boolean autenticazioneAdmin(String email, String password) {
		AdminModel admin = trovaAdminPerEmail(email);
		
		return admin != null && admin.getPassword().equals(password);
	}
}
