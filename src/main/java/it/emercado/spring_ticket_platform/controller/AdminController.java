package it.emercado.spring_ticket_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.emercado.spring_ticket_platform.model.CategoriaModel;
import it.emercado.spring_ticket_platform.model.DashboardAdmin;
import it.emercado.spring_ticket_platform.model.OperatoreModel;
import it.emercado.spring_ticket_platform.model.TicketModel;
import it.emercado.spring_ticket_platform.repository.CategoriaRepository;
import it.emercado.spring_ticket_platform.repository.OperatoreRepository;
import it.emercado.spring_ticket_platform.repository.ServizioTicket;
import it.emercado.spring_ticket_platform.repository.TicketRepository;
import it.emercado.spring_ticket_platform.servizi.CategoriaService;
import it.emercado.spring_ticket_platform.servizi.ImplemServiziAdmin;
import it.emercado.spring_ticket_platform.servizi.ServiziDashboard;
import it.emercado.spring_ticket_platform.servizi.ServizioNota;
import it.emercado.spring_ticket_platform.servizi.StatoServizioOperatore;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ImplemServiziAdmin implemServiziAdmin;
	
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
	
	@Autowired
	private ServizioTicket servizioTicket;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ServizioNota servizioNota;
	

	
	//qui si visualizza la dashboard, e eventuali eccezioni
	@GetMapping("/dashboardAdmin")
	 public String getDashboardModel(Model model) {
        try {
            DashboardAdmin dashboardModel = serviziDashboard.getDashboardModel();
            model.addAttribute("dashboardAdmin", dashboardModel);
            return "admin/dashboardAdmin";
        } catch (Exception e) {
            model.addAttribute("error", "Errore nel recupero della dashboard: " + e.getMessage());
            return "admin/errorAdmin";
        }
    }
	
	

	
	//da qui si crea un nuovo operatore
	@GetMapping("/creaOperatore")
	public String formNuovoOperatore(Model model) {
		model.addAttribute("operatore", new OperatoreModel());
		return "admin/creaOperatore";
	}
	//gestisco l'invio del modulo nuovo operatore
	@PostMapping("/salvaOperatore")
	public String creaOperatore(@Valid @ModelAttribute("operatore") OperatoreModel operatoreModel, 
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			return "admin/creaOperatore";
		}
		implemServiziAdmin.creaNuovoOperatore(operatoreModel.getEmail(), operatoreModel.getPassword());
		
		return "redirect:/admin/operatori";
	}
	
	//da qui visualizzo tutti i ticket
	@GetMapping("adminTicketList")
	public String vediTuttiTicket(Model model) {
		model.addAttribute("ticket", ticketRepository.findAll());
		
		return "admin/adminTicketList";
	}
	
	//da qui visualizzo il ticket al dettaglio
	@GetMapping("/ticket/vedi/{id}")
	public String vediDettaglioTicket(@PathVariable("id") Integer id, Model model) {
	    TicketModel ticket = servizioTicket.recuperaTicket(id);  
	    model.addAttribute("ticket", ticket);
	    model.addAttribute("numeroNote", ticket.getNote());
	    return "admin/ticketDettaglio";  
	}


	
	//modulo per modificare un ticket
	@GetMapping("/ticket/modifica/{id}")
	public String modificaTicketForm(@PathVariable("id") Integer id, Model model) {
     
        TicketModel ticket = servizioTicket.recuperaTicket(id);
        
        List<CategoriaModel> categorie = categoriaService.getAllCategorie();
        
        List<OperatoreModel> operatori = operatoreRepository.findAll();
        
        model.addAttribute("categorie", categorie);
        model.addAttribute("ticket", ticket);
        model.addAttribute("operatori", operatori);

        return "admin/modificaTicket";
	}
	
	//invio modulo di modifica ticket
	@PostMapping("/ticket/{id}/inviaModifica")
	public String caricaModificaTicket(@PathVariable("id") Integer id, 
			@Valid @ModelAttribute("ticket") TicketModel ticketModel, 
			BindingResult bindingResult, Model model) {
	    if (bindingResult.hasErrors()) {
	        return "admin/modificaTicket";
	    }
	    
	 // Recupera il ticket originale dal database
	    TicketModel ticketDaModificare = servizioTicket.recuperaTicket(id);
	    
	    ticketDaModificare.setTitolo(ticketModel.getTitolo());
	    ticketDaModificare.setDescrizione(ticketModel.getDescrizione());
	    ticketDaModificare.setCategoria(ticketModel.getCategoria());
	    ticketDaModificare.setStato(ticketModel.getStato());
	    
	    servizioTicket.salvaTicket(ticketDaModificare);
	    
	    return "redirect:/admin/ticket/vedi/" + id; 
	}

	
	//per eliminare un ticket
	@GetMapping("/ticket/elimina/{id}")
	public String eliminaTicket(@PathVariable("id") Integer id) {
		servizioTicket.eliminaTicket(id);
		 return "redirect:/admin/adminTicketList";
	}
	
	//gestione categoria ticket
	@GetMapping("/categorie")
	public String mostraCategorie(Model model) {
		model.addAttribute("categorie", categoriaRepository.findAll());
		model.addAttribute("categoria", new CategoriaModel());
		
		return "admin/categorieList";
	}
	
	
	@PostMapping("/categorie/crea")
	public String creaCategoria(@Valid @ModelAttribute("categoria") CategoriaModel categoriaModel, 
	       BindingResult bindingResult, Model model) {
	    if (bindingResult.hasErrors()) {
	        return "admin/categorieList";  
	    }
	    categoriaRepository.save(categoriaModel);
	    return "redirect:/admin/categorie";  
	}

	
	// per creare nuovo ticket
	@GetMapping("/ticket/creaTicket")
	public String formTicket(Model model) {
		model.addAttribute("ticket", new TicketModel());
		model.addAttribute("categorie", categoriaRepository.findAll());
		model.addAttribute("operatori", operatoreRepository.findAll());
		
		return "admin/createTicket";
	}
	
	//invio form crea ticket
	@PostMapping("/ticket/salvaTicket")
	public String creaTicket(@Valid @ModelAttribute("ticket") TicketModel ticketModel, 
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("categorie", categoriaRepository.findAll());
			model.addAttribute("operatori", operatoreRepository.findAll());
			
			return "/admin/createTicket";
		}
		ticketRepository.save(ticketModel);
		
		return "redirect:/admin/adminTicketList";
	}
	
	// Mostra la pagina per aggiungere una nuova nota a un ticket
	@GetMapping("/ticket/aggiungiNota/{id}")
	public String mostraAggiungiNotaForm(@PathVariable("id") Integer id, Model model) {
	    TicketModel ticket = ticketRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Ticket non trovato"));
	    model.addAttribute("ticket", ticket);
	    return "admin/aggiungiNota";  
	}

	//invio form, in futuro potrei eliminare autore e passare la mail dell'user autenticato per quell'aggiunta
	@PostMapping("/ticket/{id}/aggiungiNota")
	public String aggiungiNota(@PathVariable("id") Integer id,
	                           @RequestParam("autore") String autore,
	                           @RequestParam("nota") String testoNota,
	                           Model model) {
		
	    TicketModel ticket = ticketRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Ticket non trovato"));

	    servizioNota.addNota(ticket, autore, testoNota);


	    return "redirect:/admin/ticket/vedi/" + id;
	}
	
	//per vedere la lista delle note
	@GetMapping("/ticket/{id}/note")
	public String vediListaNote(@PathVariable("id") Integer id, Model model) {
	    TicketModel ticket = ticketRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Ticket non trovato"));
	    model.addAttribute("ticket", ticket);
	    model.addAttribute("note", ticket.getNote()); 
	    return "admin/listaNote"; 
	}


	//modifico la nota
	@PostMapping("/nota/modifica")
	public String modificaNota(@RequestParam("id") Integer id,
	                           @RequestParam("testo") String nuovoTesto) {
	    servizioNota.modificaNota(id, nuovoTesto);
	    return "redirect:/listaNote";  // Reindirizza alla lista delle note o alla pagina desiderata
	}

	//eliminola nota
	@GetMapping("/nota/elimina/{id}")
	public String eliminaNota(@PathVariable("id") Integer id) {
	    servizioNota.eliminaNota(id);
	    return "redirect:/listaNote";  
	}

	
	//qui visualizzo tutti gli operatori
	 @GetMapping("/operatori")
	    public String mostraOperatori(Model model) {
	        List<OperatoreModel> operatori = serviziDashboard.mostraListaOperatori();
	        model.addAttribute("operatori", operatori);
	        return "admin/operatoriList"; 
	    }

	
	//attivo un operatore
	@PostMapping("/operatore/attiva")
	public String attivaOperatore(@RequestParam("email") String email) {
        statoServizioOperatore.aggiornaStatoPersonale(email, true);
        return "redirect:/admin/operatori";
    }

    // Disattiva un operatore
    @PostMapping("/operatore/disattiva")
    public String disattivaOperatore(@RequestParam("email") String email, Model model) {
        try {
        	statoServizioOperatore.aggiornaStatoPersonale(email, false);
        	return "redirect:/admin/operatori";     	
        } catch (IllegalStateException e) {
        	model.addAttribute("error", e.getMessage());
        	return "admin/erroreDisattivazione";
        }
     }
	
	//per tornare alla home
	@GetMapping("/home")
	public String tornaAllaHome(Model model) {
		
		return "admin/dashboardAdmin";
	}
	
	

}

	
	

	
