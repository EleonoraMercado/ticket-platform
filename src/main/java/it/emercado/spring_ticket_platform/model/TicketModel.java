package it.emercado.spring_ticket_platform.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ticket")
public class TicketModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(name = "titolo", nullable = false)
	private String titolo;
	
	@NotEmpty
	@Column(name = "descrizione", nullable = false, length = 700)
	private String descrizione;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "stato", nullable = false)
	private StatoTicket stato;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private CategoriaModel categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_operatore")
	private OperatoreModel operatore;
	
	@OneToMany(mappedBy = "ticket")
	private List<NotaModel> note;
	
	@Column(nullable = false)
	private LocalDateTime data_creazione;
	
	@Column(name = "data_ultimaModifica", nullable =false)
	private LocalDateTime data_ultima_modifica;
	
	 @ManyToOne
	    @JoinColumn(name = "admin_id")
	    private AdminModel admin;
	 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public StatoTicket getStato() {
		return stato;
	}

	public void setStato(StatoTicket stato) {
		this.stato = stato;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	public OperatoreModel getOperatore() {
		return operatore;
	}

	public void setOperatore(OperatoreModel operatore) {
		this.operatore = operatore;
	}

	public List<NotaModel> getNote() {
		return note;
	}

	public void setNote(List<NotaModel> note) {
		this.note = note;
	}

	public LocalDateTime getData_creazione() {
		return data_creazione;
	}

	public void setData_creazione(LocalDateTime data_creazione) {
		this.data_creazione = data_creazione;
	}

	public LocalDateTime getData_ultima_modifica() {
		return data_ultima_modifica;
	}

	public void setData_ultima_modifica(LocalDateTime data_ultimaModifica) {
		this.data_ultima_modifica = data_ultimaModifica;
	}

	
	

	
}
