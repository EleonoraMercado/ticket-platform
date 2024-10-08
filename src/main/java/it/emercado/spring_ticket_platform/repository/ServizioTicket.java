package it.emercado.spring_ticket_platform.repository;

import it.emercado.spring_ticket_platform.model.StatoTicket;
import it.emercado.spring_ticket_platform.model.TicketModel;

public interface ServizioTicket {
	
void aggiornaStatoTicket(Integer id, StatoTicket nuovoStato);

TicketModel recuperaTicket(Integer id);

void eliminaTicket(Integer id);

void salvaTicket(TicketModel ticket); 
}
