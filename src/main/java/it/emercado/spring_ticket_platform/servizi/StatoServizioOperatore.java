package it.emercado.spring_ticket_platform.servizi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
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

    public void aggiornaStatoPersonale(String email, boolean statoAggiornato) {
        OperatoreModel operatore = operatoreRepository.findByEmail(email)
        		.orElseThrow(() ->  new RuntimeException("L'operatore con ID " + email + " non è stato trovato"));

        if (statoAggiornato) {
            attivaOperatore(operatore);
        } else {
            disattivaOperatoreLibero(operatore);
        }

        operatoreRepository.save(operatore);
    }

    private void attivaOperatore(OperatoreModel operatore) {
        operatore.setStatoPersonale(true);
    }

    private void disattivaOperatoreLibero(OperatoreModel operatore) {
        // Verifica se l'operatore ha ticket pendenti (DA_FARE o IN_CORSO)
        boolean haTicketPendenti = !ticketRepository.findByOperatoreEmailAndStatoIn(
                operatore.getEmail(),
                Arrays.asList(StatoTicket.DA_FARE, StatoTicket.IN_CORSO)
        ).isEmpty();

        // Se ci sono ticket pendenti, lancia un'eccezione
        if (haTicketPendenti) {
            throw new IllegalStateException("L'operatore con email " + operatore.getEmail() + " non può essere disattivato, ha dei ticket pendenti.");
        }

        // Se non ci sono ticket pendenti, disattiva l'operatore
        operatore.setStatoPersonale(false);
    }
}
