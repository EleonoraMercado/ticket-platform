package it.emercado.spring_ticket_platform.servizi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.emercado.spring_ticket_platform.model.DashboardModel;
import it.emercado.spring_ticket_platform.model.OperatoreModel;
import it.emercado.spring_ticket_platform.model.TicketModel;
import it.emercado.spring_ticket_platform.repository.OperatoreRepository;
import it.emercado.spring_ticket_platform.repository.TicketRepository;

@Service
public class ServiziDashboard {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private OperatoreRepository operatoreRepository;
	
	public DashboardModel getDashboardModel() {
		
		List<TicketModel> ticket = ticketRepository.findAll();
		List<OperatoreModel> operatore = operatoreRepository.findAll();
		int totaleTicket = ticket.size();
		int totaleOperatori = operatore.size();
		
		DashboardModel dashboardModel = new DashboardModel();
		dashboardModel.setTicket(ticket);
		dashboardModel.setOperatore(operatore);
		dashboardModel.setTotaleTicket(totaleTicket);
		dashboardModel.setTotaleOperatori(totaleOperatori);
		
		return dashboardModel;
	}
}