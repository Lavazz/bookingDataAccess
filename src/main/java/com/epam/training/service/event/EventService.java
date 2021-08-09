package com.epam.training.service.event;

import com.epam.training.model.Event;

import java.util.Date;
import java.util.List;

public interface EventService {

    Event getEventById(Long eventId);

    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    Event createEvent(Event event);

    Event updateEvent(Event event);

    void deleteEvent(Long eventId);

    List<Event> findAll();

}
