package it.emercado.spring_ticket_platform.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import it.emercado.spring_ticket_platform.model.StatoTicket;
import it.emercado.spring_ticket_platform.model.TicketModel;

public interface TicketRepository extends JpaRepository<TicketModel, Integer> {
	
List<TicketModel> findByIdAndStatoIn(Integer id_operatore, List<StatoTicket> stati);
}
