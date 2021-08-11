package com.romantulchak.bustransportation.repository;

import com.romantulchak.bustransportation.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
