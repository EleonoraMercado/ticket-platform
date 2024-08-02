package it.emercado.spring_ticket_platform.servizi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.emercado.spring_ticket_platform.model.OperatoreModel;
import it.emercado.spring_ticket_platform.model.StatoTicket;
import it.emercado.spring_ticket_platform.repository.OperatoreRepository;
import it.emercado.spring_ticket_platform.repository.TicketRepository;

@Service
public class StatoServizioOperatore {

	@Autowired
	private OperatoreRepository operatoreRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	public void AggiornaStatoPersonale(Integer id_operatore, boolean statoAggiornato) {
	OperatoreModel operatore = operatoreRepository.findById(id_operatore)
	.orElseThrow(() -> new RuntimeException("L'operatore numero " + id_operatore + "non è stato trovato"));
	
	if (statoAggiornato) {
		attivaOperatore(operatore);
	} else {
		disattivaOperatoreLibero(operatore);
	}
	
	operatoreRepository.save(operatore);
	}
	
	private void attivaOperatore(OperatoreModel operatore) {
		operatore.setStato_personale(true);
	}
	
	private void disattivaOperatoreLibero(OperatoreModel operatore) {
		boolean haTicketPendenti = !ticketRepository.findByIdAndStatoIn(operatore.getId(), Arrays.asList(StatoTicket.DA_FARE, StatoTicket.IN_CORSO)).isEmpty();
		
	if (haTicketPendenti) {
		throw new IllegalStateException("L'operatore con id " + operatore.getId() + " non può essere disattivato, ha dei ticket pendenti ");
	}
	operatore.setStato_personale(false);
	}
}
