package com.epam.training.service.ticket;

import com.epam.training.dao.ticket.TicketDao;
import com.epam.training.model.Category;
import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {


    private final TicketDao ticketDao;

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Ticket bookTicket(Long userId, Long eventId, int place, Category category) {
//        Ticket ticket = new Ticket(userId, eventId, place, category);
//
//        if (!isPlaceFree(ticket)) {
//            log.error(
//                    String.format("Ticket booking failed. Place %s is already booked.", ticket.getPlace()));
//            throw new IllegalStateException();
//        }
//        long id = new Random().nextLong();
//        if (isExist(id)) {
//            bookTicket(userId, eventId, place, category);
//        } else {
//            ticket.setId(id);
//        }
//        ticketDao.bookTicket(ticket);
//        log.info("Ticket booked successfully. Ticket details: {}", ticket.toString());
//
//        return ticketDao.getTicketById(id);
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        List<Ticket> tickets = new ArrayList<>();

        for (Ticket ticket : ticketDao.findAll()) {
            if (ticket.getUserId() == user.getId()) {
                tickets.add(ticket);
            }
        }

        List<Ticket> sortTickets = tickets.stream()
                .sorted(Comparator.comparingLong(Ticket::getId))
                .collect(Collectors.toList());

        log.info(String.format("%s ticket(s) found: ", sortTickets.size()));

        return sortTickets;
    }


    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        List<Ticket> tickets = new ArrayList<>();

        for (Ticket ticket : ticketDao.findAll()) {
            if (ticket.getEventId() == event.getId()) {
                tickets.add(ticket);
            }
        }

        List<Ticket> sortTickets = tickets.stream()
                .sorted(Comparator.comparingLong(Ticket::getId))
                .collect(Collectors.toList());

        log.info(String.format("%s ticket(s) found: ", tickets.size()));

        return sortTickets;
    }


    @Override
    public void cancelTicket(Long ticketId) {
        Ticket ticket = ticketDao.findById(ticketId).get();
        ticketDao.delete(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }


    // private method for place check
    private boolean isPlaceFree(Ticket ticket) {

        boolean isPlaceFree = true;

        for (Ticket storedTicket : ticketDao.findAll()) {
            if (storedTicket.getEventId() == ticket.getEventId()
                    && storedTicket.getPlace() == ticket.getPlace()) {
                isPlaceFree = false;
            }
        }
        return isPlaceFree;
    }


}
