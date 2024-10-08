package it.emercado.spring_ticket_platform.model;

import java.util.List;

public class DashboardAdmin {

	private List<TicketModel> ticket;
	private List<OperatoreModel> operatore;
	private int totaleTicket;
	private int totaleOperatori;
	
	
	public List<TicketModel> getTicket() {
		return ticket;
	}
	public void setTicket(List<TicketModel> ticket) {
		this.ticket = ticket;
	}
	public List<OperatoreModel> getOperatore() {
		return operatore;
	}
	public void setOperatore(List<OperatoreModel> operatore) {
		this.operatore = operatore;
	}
	public int getTotaleTicket() {
		return totaleTicket;
	}
	public void setTotaleTicket(int totaleTicket) {
		this.totaleTicket = totaleTicket;
	}
	public int getTotaleOperatori() {
		return totaleOperatori;
	}
	public void setTotaleOperatori(int totaleOperatori) {
		this.totaleOperatori = totaleOperatori;
	}
	
	
	
}
