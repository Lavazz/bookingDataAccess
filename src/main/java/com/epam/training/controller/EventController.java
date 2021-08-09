package com.epam.training.controller;

import com.epam.training.facade.BookingFacade;
import com.epam.training.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/allEvents")
    public String getAllTickets(Model model) {
        log.debug("Get all tickets");
        List<Event> allEvents = bookingFacade.getAllEvents();
        model.addAttribute("allEvents", allEvents);
        model.addAttribute("heading", "List of all events in DB");
        return "list_events";
    }


    @GetMapping("events/delete/{id}")
    public String deleteEvent(@PathVariable("id") long id, Model model) {
        bookingFacade.deleteEvent(id);
        return "redirect:/events/allEvents";
    }

    @GetMapping("events/new")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("new_event");
        log.debug("Create new event ");
        return modelAndView;
    }


    @PostMapping("events/events/create")
    public String name(@RequestParam("title") String title, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-dd-MM") Date date) {
        log.debug("Create event with title ({}) and date ({})", title, date);
        Event event = new Event(title, date);
        bookingFacade.createEvent(event);
        log.info("Method start. EventController.");
        return  "redirect:/events/allEvents";
    }


//    @PutMapping("/{id}")
//    public ModelAndView updateEvent(@PathVariable long id,
//                                    @RequestParam(required = false) String title,
//                                    @RequestParam(required = false) String date) {
//        ModelAndView modelAndView = new ModelAndView("entities");
//        Event event = bookingFacade.getEventById(id);
//        if (Objects.nonNull(event)) {
//            event.setTitle(title);
//            event.setDate(parseDate(date));
//            event = bookingFacade.updateEvent(event);
//            modelAndView.addObject("entities", event);
//            modelAndView.addObject("message", "update entity");
//        } else {
//            modelAndView.addObject("message", "not found entity");
//        }
//        return modelAndView;
//    }



//    @GetMapping("/{id}")
//    public ModelAndView getEventById(@PathVariable long id) {
//        ModelAndView modelAndView = new ModelAndView("entities");
//        Event event = bookingFacade.getEventById(id);
//        if (Objects.nonNull(event)) {
//            modelAndView.addObject("entities", event);
//            modelAndView.addObject("message", "found entity");
//        } else {
//            modelAndView.addObject("message", "not found entity");
//        }
//        return modelAndView;
//    }
//
//    @GetMapping("/title/{title}")
//    public ModelAndView getEventsByTitle(@PathVariable String title,
//                                         @RequestParam(required = false, defaultValue = "100") int pageSize,
//                                         @RequestParam(required = false, defaultValue = "1") int pageNum) {
//        ModelAndView modelAndView = new ModelAndView("entities");
//        List<Event> events = bookingFacade.getEventsByTitle(title, pageSize, pageNum);
//        if (!events.isEmpty()) {
//            modelAndView.addObject("entities", events);
//            modelAndView.addObject("message", "found entity");
//        } else {
//            modelAndView.addObject("message", "not found entity");
//        }
//        return modelAndView;
//    }
//
//    @GetMapping("/date/{date}")
//    public ModelAndView getEventsByDate(@PathVariable String date,
//                                        @RequestParam(required = false, defaultValue = "100") int pageSize,
//                                        @RequestParam(required = false, defaultValue = "1") int pageNum) {
//        ModelAndView modelAndView = new ModelAndView("entities");
//        List<Event> events = bookingFacade.getEventsForDay(parseDate(date), pageSize, pageNum);
//        if (!events.isEmpty()) {
//            modelAndView.addObject("entities", events);
//            modelAndView.addObject("message", "found entity");
//        } else {
//            modelAndView.addObject("message", "not found entity");
//        }
//        return modelAndView;
//    }

    private Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formatter.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}
