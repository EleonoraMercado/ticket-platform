package it.emercado.spring_ticket_platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.emercado.spring_ticket_platform.model.OperatoreModel;

public interface OperatoreRepository extends JpaRepository<OperatoreModel, Integer> {
	
Optional<OperatoreModel> findById(Integer id);
OperatoreModel findByEmail(String email);
}
