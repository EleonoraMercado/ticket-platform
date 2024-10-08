package it.emercado.spring_ticket_platform.model;

import java.util.List;

public class DashboardOperatore {

    private List<TicketModel> ticketAssegnati; // Lista di ticket assegnati all'operatore
    private List<TicketModel> ticketCompletati; // Lista di ticket completati dall'operatore
    private List<TicketModel> ticketInSospeso; // Lista di ticket in sospeso
    private OperatoreModel operatore;
    

    public List<TicketModel> getTicketAssegnati() {
        return ticketAssegnati;
    }

    public void setTicketAssegnati(List<TicketModel> ticketAssegnati) {
        this.ticketAssegnati = ticketAssegnati;
    }

    public List<TicketModel> getTicketCompletati() {
        return ticketCompletati;
    }

    public void setTicketCompletati(List<TicketModel> ticketCompletati) {
        this.ticketCompletati = ticketCompletati;
    }

    public List<TicketModel> getTicketInSospeso() {
        return ticketInSospeso;
    }

    public void setTicketInSospeso(List<TicketModel> ticketInSospeso) {
        this.ticketInSospeso = ticketInSospeso;
    }
    
    public OperatoreModel getOperatore() {
    	return operatore;
    }
    
    public void setOperatore(OperatoreModel operatore) {
    	this.operatore = operatore;
    }
}
