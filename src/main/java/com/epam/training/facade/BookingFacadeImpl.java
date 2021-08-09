package com.epam.training.facade;

import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.User;
import com.epam.training.service.event.EventService;
import com.epam.training.service.ticket.TicketService;
import com.epam.training.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookingFacadeImpl implements BookingFacade {

    private final UserService userService;

    private final EventService eventService;

    private final TicketService ticketService;


    @Override
    public User getUserById(long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }


    @Override
    public List<User> getAllUsers() {
        return userService.findAll();
    }


    //

    @Override
    public void deleteEvent(long eventId) {
         eventService.deleteEvent(eventId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketService.findAll();
    }

    @Override
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

}

