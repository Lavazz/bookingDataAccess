package com.epam.training.service.event;

import com.epam.training.dao.event.EventDao;
import com.epam.training.exception.NoEntityException;
import com.epam.training.model.Event;
import com.epam.training.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private final EventDao eventDao;

    @Override
    public Event getEventById(Long eventId) {
        Event event = eventDao.findById(eventId).orElseThrow(() -> new NoEntityException("Event not found"));
        log.info("Event found: {}", event.toString());
        return event;
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        List<Event> eventList = eventDao.findAll();
        return eventList.stream().filter(event -> event.getTitle().equals(title))
                .skip(pageNum)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        List<Event> eventList = eventDao.findAll();
        return eventList.stream().filter(event -> event.getDate().equals(day))
                .skip(pageNum)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(Event event) {
        long id = new Random().nextLong();
        if (isExist(id)) {
            createEvent(event);
        } else {
            event.setId(id);
        }

        Event newEvent = eventDao.save(event);
        log.info("Event created successfully. event details:{} ", event.toString());
        return newEvent;
    }

    @Override
    public Event updateEvent(Event event) {
        //  return eventDao.updateEvent(event);
        return null;
    }

    @Override
    public void deleteEvent(Long eventId) {
        Event event = getEventById(eventId);
        eventDao.delete(event);
    }

    @Override
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    boolean isExist(long id) {
        List<Event> eventList = eventDao.findAll();
        for (Event event : eventList) {
            if (event.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
