package it.emercado.spring_ticket_platform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.emercado.spring_ticket_platform.model.DashboardOperatore;
import it.emercado.spring_ticket_platform.model.NotaModel;
import it.emercado.spring_ticket_platform.model.OperatoreModel;
import it.emercado.spring_ticket_platform.model.StatoTicket;
import it.emercado.spring_ticket_platform.model.TicketModel;
import it.emercado.spring_ticket_platform.repository.OperatoreRepository;
import it.emercado.spring_ticket_platform.repository.TicketRepository;
import it.emercado.spring_ticket_platform.servizi.ImplemServizioTicket;
import it.emercado.spring_ticket_platform.servizi.ServizioNota;
import it.emercado.spring_ticket_platform.servizi.StatoServizioOperatore;

@Controller
@RequestMapping("/operatore")
public class OperatoreController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private OperatoreRepository operatoreRepository;
    
    @Autowired
    private StatoServizioOperatore statoServizioOperatore;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @Autowired
    private ServizioNota servizioNota;
    
    @Autowired
    private ImplemServizioTicket implemServizioTicket;

    // Metodo per visualizzare la dashboard dell'operatore
    @GetMapping("/dashboardOperatore")
    public String mostraDashboard(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
    	
    	Optional<OperatoreModel> operatoreOpt = operatoreRepository.findByEmail(email); //Opt=Optional
    	
    	if (operatoreOpt.isPresent()) {
    		OperatoreModel operatore = operatoreOpt.get();
    		model.addAttribute("operatore", operatore);
    	} else {
    		return "operatore/error";
    	}
    	
    	
	        // Crea il modello DashboardOperatore
	        DashboardOperatore dashboardOperatore = new DashboardOperatore();
	        dashboardOperatore.setTicketAssegnati(ticketRepository.findByStato(StatoTicket.DA_FARE));
	        dashboardOperatore.setTicketCompletati(ticketRepository.findByStato(StatoTicket.COMPLETATO));
	        dashboardOperatore.setTicketInSospeso(ticketRepository.findByStato(StatoTicket.IN_CORSO));
	        
	        // Aggiungi il modello DashboardOperatore al Model
	        model.addAttribute("dashboardOperatore", dashboardOperatore);
	
	        return "operatore/dashboardOperatore";
	    }
	
	    // Mostra tutti i ticket assegnati all'operatore
	    @GetMapping("/operatoreTicketList")
	    public String mostraTicketOperatore( Model model) {
	    	
	    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	    String email = authentication.getName();
	    	
	    	List<TicketModel> tickets = ticketRepository.findByOperatoreEmail(email);
	
	        if (tickets.isEmpty()) {
	            return "operatore/errorOperatore"; // Una pagina di errore in caso di operatore non trovato
	        }
	
	        model.addAttribute("tickets", tickets);
	        return "operatore/operatoreTicketList";
	    }
	    
	    
	    // Visualizza il dettaglio di un ticket specifico
	    @GetMapping("/ticket/vedi/{id}")
	    public String vediDettaglioTicket(@PathVariable("id") Integer id, Model model) {
	        // Recupera il ticket utilizzando il servizio dei ticket
	        TicketModel ticket = implemServizioTicket.recuperaTicket(id);
	        
	        // Aggiungi il ticket e il numero di note al modello
	        model.addAttribute("ticket", ticket);
	        model.addAttribute("numeroNote", ticket.getNote().size()); 
	        return "operatore/operatoreDettaglioTicket";  
	    
	    }
	    
	
	    // Aggiorna lo stato di un ticket specifico
	    @PostMapping("/ticket/{id}/aggiornaStato")
	    public String aggiornaStatoTicket(@PathVariable("id") Integer id, 
	                                      @RequestParam("nuovoStato") StatoTicket nuovoStato,
	                                      Model model) {
	        TicketModel ticket = ticketRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Ticket non trovato"));
	
	        ticket.setStato(nuovoStato);
	        ticketRepository.save(ticket);
	
	        return "redirect:/operatore/ticket/vedi/" + id;
	    }
	    
	 // Mostra la pagina per aggiungere una nuova nota a un ticket
		@GetMapping("/ticket/{id}/nuovaNota")
		public String mostraAggiungiNotaForm(@PathVariable("id") Integer id, Model model) {
		    TicketModel ticket = ticketRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Ticket non trovato"));
		    
		    model.addAttribute("nota", new NotaModel());
		    model.addAttribute("ticket", ticket);
		    return "operatore/operatoreAggiungiNota";  
		}

		//invio form, in futuro potrei eliminare autore e passare la mail dell'user autenticato per quell'aggiunta
		@PostMapping("/ticket/{id}/salvaNota")
		public String aggiungiNota(@PathVariable("id") Integer id,
		                           @RequestParam("autore") String autore,
		                           @RequestParam("nota") String testoNota,
		                           Model model) {
			
		    TicketModel ticket = ticketRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Ticket non trovato"));

		    servizioNota.addNota(ticket, autore, testoNota);


		    return "redirect:/operatore/ticket/vedi/" + id;
		}
		
		//per vedere la lista delle note
		@GetMapping("/ticket/{id}/note")
		public String vediListaNote(@PathVariable("id") Integer id, Model model) {
		
		    TicketModel ticket = ticketRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Ticket non trovato"));
		    model.addAttribute("ticket", ticket);
		    model.addAttribute("note", ticket.getNote()); 
		    return "operatore/operatoreListaNote"; 
		}


		//modifico la nota
		@PostMapping("/nota/modifica")
		public String modificaNota(@RequestParam("id") Integer id,
								   @RequestParam("id_ticket") Integer id_ticket,
		                           @RequestParam("testo") String nuovoTesto) {
		    servizioNota.modificaNota(id, nuovoTesto);
		    return "redirect:/operatore/ticket/" + id_ticket + "/note";
		}

		//elimino la nota
		@GetMapping("/nota/elimina/{id}")
		public String eliminaNota(@PathVariable("id") Integer id) {
		    servizioNota.eliminaNota(id);
		    return "redirect:/operatore/ticket/{id}/note";
		}
		
		//Mostra il profilo dell'operatore
		@GetMapping("/profilo")
		public String visualizzaProfiloOperatore(Model model) {
		    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String email = authentication.getName();
		    
		    Optional<OperatoreModel> operatoreOpt = operatoreRepository.findByEmail(email);

		    if (operatoreOpt.isEmpty()) {
		        return "operatore/errorOperatore";
		    }

		    OperatoreModel operatore = operatoreOpt.get();
		    model.addAttribute("operatore", operatore);

		    return "operatore/profilo";
		}

	
	 // Modifica il profilo dell'operatore
	    @GetMapping("/profilo/edit")
	    public String mostraProfiloOperatoreForm(Model model) {
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();
	    	
	    	Optional<OperatoreModel> operatoreOpt = operatoreRepository.findByEmail(email);
	        
	        if (operatoreOpt.isEmpty()) {
	            return "operatore/errorOperatore"; 
	        }
	        
	        OperatoreModel operatore = operatoreOpt.get();
	        model.addAttribute("operatore", operatore);
	        
	        return "operatore/profiloForm"; 
	    }

	    
	    // Salva le modifiche al profilo dell'operatore
	    @PostMapping("/profilo/update")
	    public String aggiornaProfilo(@RequestParam("email") String email,
	                                  @RequestParam("password") String password,
	                                  @RequestParam("stato") boolean stato,
	                                  Model model) {
	        Optional<OperatoreModel> operatoreOpt = operatoreRepository.findByEmail(email);
	
	        if (operatoreOpt.isEmpty()) {
	            return "operatore/errorOperatore"; // Una pagina di errore in caso di operatore non trovato
	        }
	
	        OperatoreModel operatoreModel = operatoreOpt.get();
	
	        try {
	            // Aggiorna lo stato personale 
	            statoServizioOperatore.aggiornaStatoPersonale(email, stato);
	            
	        } catch (IllegalStateException e) {
	            // Se l'operatore ha ticket pendenti o da fare e non può essere disattivato, mostra un errore
	            model.addAttribute("error", e.getMessage());
	            model.addAttribute("operatore", operatoreModel);
	            return "operatore/operatoreErroreDisattivazione"; 
	        }

	        // Codifica la password 
	        if (!password.isBlank()) {
	            String encodedPassword = passwordEncoder.encode(password);
	            operatoreModel.setPassword(encodedPassword);
	        }

	        operatoreRepository.save(operatoreModel);
	
	        return "redirect:/operatore/profilo";
	    }
	
	    // Modifica un ticket specifico
	    @GetMapping("/ticket/{id}/edit")
	    public String modificaTicket(@PathVariable("id") Integer id, Model model) {
	        TicketModel ticket = ticketRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Ticket non trovato"));
	
	        model.addAttribute("ticket", ticket);
	        return "operatore/modificaTicket";
	    }
	
	    // Salva le modifiche al ticket
	    @PostMapping("/ticket/{id}/save")
	    public String salvaModificheTicket(@PathVariable("id") Integer id, 
	                                       @RequestParam("descrizione") String descrizione,
	                                       Model model) {
	        TicketModel ticket = ticketRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Ticket non trovato"));
	
	        ticket.setDescrizione(descrizione);
	        ticketRepository.save(ticket);
	
	        return "redirect:/operatore/ticket/" + id;
	    }
	    
	    
}

