package com.epam.training.facade;


import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.User;

import java.util.List;

/**
 * Groups together all operations related to tickets booking.
 * Created by maksym_govorischev.
 */
public interface BookingFacade {

    User getUserById(long userId);

    /**
     * Gets user by its email. Email is strictly matched.
     *
     * @return User.
     */
    User getUserByEmail(String email);

    /**
     * Get list of users by matching name. Name is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param name     Users name or it's part.
     * @param pageSize Pagination param. Number of users to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of users.
     */
    List<User> getUsersByName(String name, int pageSize, int pageNum);

    /**
     * Creates new user. User id should be auto-generated.
     *
     * @param user User data.
     * @return Created User object.
     */
    User createUser(User user);


    User updateUser(User user);

   void deleteUser(Long userId);

    List<User> getAllUsers();

    //

    List<Ticket> getAllTickets();

    List<Event>  getAllEvents();

   void deleteEvent(long eventId);

    Event createEvent(Event event);

}

