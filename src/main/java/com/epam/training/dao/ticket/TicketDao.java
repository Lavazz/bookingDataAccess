package com.epam.training.dao.ticket;

import com.epam.training.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDao extends JpaRepository<Ticket, Long> {
}

