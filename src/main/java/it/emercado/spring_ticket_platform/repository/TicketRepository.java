package it.emercado.spring_ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import it.emercado.spring_ticket_platform.model.StatoTicket;
import it.emercado.spring_ticket_platform.model.TicketModel;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel, Integer> {

	//questo mi serve per quando creo il modello che verrà aggiunto alla dashboard
	//che sarà, completato, assegnato, in sospeso
	List<TicketModel> findByStato(StatoTicket stato);
	
	//per trovare tutti i ticket di ogni stato assegnato a un operatore
	List<TicketModel> findByOperatoreEmailAndStato(String email, StatoTicket stato);

	
	//questo mi serve da utilizzare nel metodo che cerca gli operatori con ticket pendenti

	List<TicketModel> findByOperatoreEmailAndStatoIn(@Param("email") String email, @Param("stato") List<StatoTicket> stati);
	
	//questo metodo recupera i ticket assegnati a un operatore senza lo stato specifico
	
	List<TicketModel> findByOperatoreEmail(String email);


}
