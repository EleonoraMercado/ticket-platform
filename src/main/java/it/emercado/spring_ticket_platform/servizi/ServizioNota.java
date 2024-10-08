package it.emercado.spring_ticket_platform.servizi;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.emercado.spring_ticket_platform.model.NotaModel;
import it.emercado.spring_ticket_platform.model.TicketModel;
import it.emercado.spring_ticket_platform.repository.NotaRepository;
import it.emercado.spring_ticket_platform.repository.TicketRepository;

@Service
public class ServizioNota {
	
	@Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private NotaRepository notaRepository;

  //metodo per aggiunere una nuova nota
    public void addNota(TicketModel ticket, String autore, String testoNota) {
        NotaModel nota = new NotaModel();
        nota.setAutore(autore);
        nota.setTesto(testoNota);
        nota.setData_creazione(LocalDateTime.now());
        nota.setTicket(ticket); // Associa la nota al ticket

        notaRepository.save(nota);

        // Aggiungi la nota al ticket e aggiorna il ticket
        ticket.getNote().add(nota);
        ticketRepository.save(ticket);  
    }
    
 // Metodo per eliminare una nota
    public void eliminaNota(Integer id) {
        NotaModel nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota non trovata"));
        notaRepository.delete(nota);
    }
    
 // Metodo per modificare una nota esistente
    public void modificaNota(Integer id, String nuovoTesto) {
        NotaModel nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota non trovata"));
        nota.setTesto(nuovoTesto);
        nota.setData_creazione(LocalDateTime.now()); // Aggiorna la data di modifica
        notaRepository.save(nota);
    }
    
}
