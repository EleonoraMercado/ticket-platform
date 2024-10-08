package it.emercado.spring_ticket_platform.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "nota")
public class NotaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_ticket")
	private TicketModel ticket;
	
	@Column(name = "autore", nullable = false)
	private String autore;
	
	@Column(name = "testo", nullable = false)
	private String testo;
	
	@Column(name = "data_creazione", nullable = false)
	private LocalDateTime data_creazione;
	
	@ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminModel admin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TicketModel getTicket() {
		return ticket;
	}

	public void setTicket(TicketModel ticket) {
		this.ticket = ticket;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public LocalDateTime getData_creazione() {
		return data_creazione;
	}

	public void setData_creazione(LocalDateTime data_creazione) {
		this.data_creazione = data_creazione;
	}
	
	

}
