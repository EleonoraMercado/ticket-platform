package it.emercado.spring_ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.emercado.spring_ticket_platform.model.AdminModel;

public interface AdminRepository extends JpaRepository<AdminModel, Integer> {

	AdminModel findByEmail(String email);
}
