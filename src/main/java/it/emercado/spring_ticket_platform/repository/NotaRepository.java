package it.emercado.spring_ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.emercado.spring_ticket_platform.model.NotaModel;

public interface NotaRepository extends JpaRepository<NotaModel, Integer>{

}