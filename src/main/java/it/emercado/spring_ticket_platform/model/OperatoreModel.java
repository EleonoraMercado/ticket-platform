package it.emercado.spring_ticket_platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "operatore")
public class OperatoreModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "L'email è obbligatoria")
	@Email(message = "Formato email non valido")
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@NotEmpty(message = "La password è obbligatoria")
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "stato_personale", nullable = false)
	private boolean statoPersonale = true;
	
	 @ManyToOne
	 @JoinColumn(name = "admin_id", nullable = true)  //ho messo true per semplificare l'insorgenza di errori, e non doverlo includere nel form per ora.
	 private AdminModel admin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatoPersonale() {
		return statoPersonale;
	}

	public void setStatoPersonale(boolean statoPersonale) {
		this.statoPersonale = statoPersonale;
	}
	
	
}
