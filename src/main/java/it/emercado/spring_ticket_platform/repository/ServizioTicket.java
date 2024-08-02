package it.emercado.spring_ticket_platform.repository;

import it.emercado.spring_ticket_platform.model.StatoTicket;
import it.emercado.spring_ticket_platform.model.TicketModel;

public interface ServizioTicket {
void aggiornaStatoTicket(int id, StatoTicket nuovoStato);
TicketModel recuperaTicket(int id);
}
