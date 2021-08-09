package com.epam.training.service.ticket;

import com.epam.training.model.*;
import com.epam.training.model.Ticket;

import java.util.List;

public interface TicketService {

    Ticket bookTicket(Long userId, Long eventId, int place, Category category);

    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

   void cancelTicket(Long ticketId);

    List<Ticket> findAll();
}
