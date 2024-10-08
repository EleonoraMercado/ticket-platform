package it.emercado.spring_ticket_platform.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Admin")
public class AdminModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@NotBlank(message = "L'email non puo essere vuota")
	@Email(message = "Il formato dell'email non è valido")
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	
	@NotBlank(message = "La password non può essere vuota")
	@Size(min = 8, message = "La password deve contenere almeno 6 caratteri")
	//da riprendere in un secondo momento
	@Pattern(
			regexp = "\n"
					+ "  ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$\n"
					+ "", message = "La password deve contenere una cifra da 1 a 9, una lettera minuscola, una lettera maiuscola, un carattere speciale, nessuno spazio e deve essere lunga da 8 a 16 caratteri.")
	@Column(name = "password", nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "admin")
    private List<TicketModel> tickets;
	
	@OneToMany(mappedBy = "admin")
    private List<OperatoreModel> operatori;
	
	@OneToMany(mappedBy = "admin")
    private List<NotaModel> nota;
	
	@OneToMany(mappedBy = "admin")
    private List<CategoriaModel> categoria;

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
	
	
}
