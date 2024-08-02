package it.emercado.spring_ticket_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.emercado.spring_ticket_platform.model.CategoriaModel;
import it.emercado.spring_ticket_platform.model.DashboardModel;
import it.emercado.spring_ticket_platform.model.OperatoreModel;
import it.emercado.spring_ticket_platform.model.TicketModel;
import it.emercado.spring_ticket_platform.repository.CategoriaRepository;
import it.emercado.spring_ticket_platform.repository.OperatoreRepository;
import it.emercado.spring_ticket_platform.repository.TicketRepository;
import it.emercado.spring_ticket_platform.servizi.ImplemServiziAdmin;
import it.emercado.spring_ticket_platform.servizi.ImplemServizioTicket;
import it.emercado.spring_ticket_platform.servizi.ServiziDashboard;
import it.emercado.spring_ticket_platform.servizi.StatoServizioOperatore;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ImplemServiziAdmin implemServiziAdmin;
	
	@Autowired
	private ImplemServizioTicket implemServizioTicket;
	
	@Autowired
	private ServiziDashboard serviziDashboard;
	
	@Autowired
	private StatoServizioOperatore statoServizioOperatore;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private OperatoreRepository operatoreRepository;
	
	//qui si visualizza la dashboard, e eventuali eccezioni
	@GetMapping("/dashboard")
	public String getDashboard(Model model) {
		try {
			DashboardModel dashboardModel = serviziDashboard.getDashboardModel();
			model.addAttribute("dashboard", dashboardModel);
			return "admin/dashboard";	
		} catch (Exception e) {
			model.addAttribute("error", "Errore di visualizzazione dashboard: " + e.getMessage());
			return "admin/error";
		}
			
	}
	
	//da qui si crea un nuovo operatore
	@GetMapping("/creaOperatore")
	public String formNuovoOperatore(Model model) {
		model.addAttribute("operatore", new OperatoreModel());
		return "admin/creaOperatore";
	}
	//gestisco l'invio del modulo nuovo operatore
	@PostMapping("/admin/creaOperatore")
	public String creaOperatore(@Valid @ModelAttribute("operatore") OperatoreModel operatoreModel, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			
			return "admin/creaOperatore";
		}
		implemServiziAdmin.creaNuovoOperatore(operatoreModel.getEmail(), operatoreModel.getPassword());
		
		return "redirect:/dashboard";
	}
	
	//da qui isualizzo tutti i ticket
	@GetMapping("/admin/ticket")
	public String vediTuttiTicket(Model model) {
		model.addAttribute("ticket", ticketRepository.findAll());
		
		return "admin/ticketList";
	}
	
	//modulo per modificare un ticket
	@GetMapping("/admin/ticket/modifica/{id}")
	public String formModificaTicket(@PathVariable("id") Integer id, Model model) {
		TicketModel ticket = implemServizioTicket.recuperaTicket(id);
		model.addAttribute("ticket", ticket);
				
		 return "admin/modificaTicket";
	}
	
	//invio modulo di modifica ticket
	@PostMapping("/admin/ticket/modifica/{id}")
	public String caricaModificaTicket(@PathVariable("id") Integer id, @Valid @ModelAttribute("ticket") TicketModel ticketModel, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/modificaTicket";
		}
		ticketRepository.save(ticketModel);
		
		return "redirect:/admin/ticket";
	}
	
	//gestione categoria ticket
	@GetMapping("admin/categorie")
	public String mostraCategorie(Model model) {
		model.addAttribute("categorie", categoriaRepository.findAll());
		
		return "admin/categorieList";
	}
	
	//form per nuova categoria
	@GetMapping("/admin/categorie/crea")
	public String formCategorie(Model model) {
		model.addAttribute("categoria", new CategoriaModel());
		
		return "admin/creaCategoria";
	}
	
	//invio del modulo nuova categoria
	public String creaCategoria(@Valid @ModelAttribute("categoria") CategoriaModel categoriaModel, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			
			return "admin/creaCategoria";
		}
		categoriaRepository.save(categoriaModel);
		return "redirect:/admin/categorie";
		
	}
	
	//form per creare nuovo ticket
	@GetMapping("/admin/ticket/crea")
	public String formTicket(Model model) {
		model.addAttribute("ticket", new TicketModel());
		model.addAttribute("categorie", categoriaRepository.findAll());
		
		return "admin/creaTicket";
	}
	
	//invio form crea ticket
	@PostMapping("/admin/ticket/crea")
	public String creaTicket(@Valid @ModelAttribute("ticket") TicketModel ticketModel, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("categorie", categoriaRepository.findAll());
			
			return "admin/creaTicket";
		}
		ticketRepository.save(ticketModel);
		
		return "redirect:/admin/ticket";
	}
	
	//qui visualizzo tutti gli operatori
	@GetMapping("/admin/operatori")
	public String mostraOperatori(Model model) {
	model.addAttribute("operatori", operatoreRepository.findAll());
	
	return "admin/operatoriList";
	}
	
	//attivo un operatore
	@PostMapping("/admin/opratore/attiva/{id}")
	public String attivaOperatore(@PathVariable("id") Integer id) {
		statoServizioOperatore.AggiornaStatoPersonale(id,  true);
		
		return "redirect:/admin/operatori";
	}
	
	//disattiva un operatore
	@PostMapping("/admin/operatore/disattiva/{id}")
	public String disattivaOperatore(@PathVariable("id") Integer id) {
		statoServizioOperatore.AggiornaStatoPersonale(id, false);
		
		return "redirect:/admin/operatori";
	}
}

	
	

	
