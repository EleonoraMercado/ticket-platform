package it.emercado.spring_ticket_platform.servizi;

import it.emercado.spring_ticket_platform.model.StatoTicket;

public class GestioneStatoTicket {

	private StatoTicket statoCorrente;
	
	//Costruttore
	public GestioneStatoTicket(StatoTicket statoIniziale) {
		this.statoCorrente = statoIniziale;
		System.out.println("Il ticket è in stato: " + statoIniziale);
	}
	
	public StatoTicket getStatoCorrente() {
		return statoCorrente;
	}
	
	public void aggiornaStato(StatoTicket nuovoStato) {
		if (this.statoCorrente == nuovoStato) {
			System.out.println("Lo stato del ticket si è aggiornato in: " + nuovoStato);
			return;
		}
		
		switch (nuovoStato) {
			case DA_FARE:
				System.out.println("Il ticket è da " + nuovoStato);
				break;
			case IN_CORSO:
				if (statoCorrente == StatoTicket.DA_FARE) {
					iniziaLavorazione();
					statoCorrente = nuovoStato;
				} else {
					System.out.println("Il ticket sarà considerato in corso solo una volta iniziato.");
				}
				break;
			case COMPLETATO:
				if (statoCorrente == StatoTicket.IN_CORSO) {
					finalizzaTicket();
					statoCorrente = nuovoStato;
				} else {
					System.out.println("Per completare il ticket devi prima averlo elaborato.");
				}
				break;
			case ANNULLATO:
				if (statoCorrente != StatoTicket.COMPLETATO) {
					annullaTicket();
					statoCorrente = nuovoStato;
				} else {
					System.out.println("I ticket completati non possono essere anullai");
				}
				break;
		}
	}
	
	private void iniziaLavorazione() {
System.out.println("LAvorazione ticket in corso.");
	}
	
	private void finalizzaTicket() {
		System.out.println("Ticket completato");
	}
	
	private void annullaTicket() {
		System.out.println("Ticket annullato");
	}
}
