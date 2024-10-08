package it.emercado.spring_ticket_platform.servizi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.emercado.spring_ticket_platform.model.StatoTicket;
import it.emercado.spring_ticket_platform.model.TicketModel;
import it.emercado.spring_ticket_platform.repository.ServizioTicket;
import it.emercado.spring_ticket_platform.repository.TicketRepository;

@Service
public class ImplemServizioTicket implements ServizioTicket {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public void aggiornaStatoTicket(Integer id, StatoTicket nuovoStato) {
		TicketModel ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket non trovato nella lista"));
		
		GestioneStatoTicket gestioneStato = new GestioneStatoTicket(ticket.getStato());
		gestioneStato.aggiornaStato(nuovoStato);
		ticket.setStato(nuovoStato);
		ticketRepository.save(ticket);
	}
	
	
	@Override
	public TicketModel recuperaTicket(Integer id) {
		return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket non trovato"));
		
	}
	
	@Override
    public void eliminaTicket(Integer id) {
        ticketRepository.deleteById(id);
    }
	
	@Override
	public void salvaTicket(TicketModel ticket) {
	    ticketRepository.save(ticket);
	}
}
