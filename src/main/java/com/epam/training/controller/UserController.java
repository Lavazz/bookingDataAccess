package com.epam.training.controller;

import com.epam.training.facade.BookingFacade;
import com.epam.training.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    private final BookingFacade bookingFacade;

    @GetMapping("/allUsers")
    public String getAllUsers(Model model) {

        List<User> allUsers = bookingFacade.getAllUsers();
        log.debug("get all users => {}", allUsers);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("heading", "List of all users in DB");
        log.info("Method start. UserController (-- / --)");
        return "list_users";
    }


    @GetMapping("users/new")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("new_user");
        log.debug("Create new user ");
        return modelAndView;
    }


    @PostMapping("users/users/create")
    public String name(@RequestParam("name") String name, @RequestParam("email") String email) {
        log.debug("Create user with name ({}) and email ({})", name, email);
        User user = new User(name, email);
        bookingFacade.createUser(user);
        log.info("Method start. UserController.");
        return  "redirect:/users/allUsers";
    }

    @PutMapping("/{id}")
    public ModelAndView updateUser(@PathVariable long id,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String email) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = bookingFacade.getUserById(id);
        if (Objects.nonNull(user)) {
            user.setName(name);
            user.setEmail(email);
            user = bookingFacade.updateUser(user);
            modelAndView.addObject("entities", user);
            modelAndView.addObject("message", "update entities");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("users/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = bookingFacade.getUserById(id);
       model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("users/edit/users/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user, Model model) {
        user.setId(id);
        bookingFacade.updateUser(user);
        return "redirect:/users/allUsers";
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        bookingFacade.deleteUser(id);
        return "redirect:/users/allUsers";
    }


    @GetMapping("/{id}")
    public ModelAndView getUserById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = bookingFacade.getUserById(id);
        if (Objects.nonNull(user)) {
            modelAndView.addObject("entities", user);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }


    @GetMapping("/name/{name}")
    public ModelAndView getUsersByName(@PathVariable String name,
                                       @RequestParam(required = false, defaultValue = "100") int pageSize,
                                       @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<User> users = bookingFacade.getUsersByName(name, pageSize, pageNum);
        if (!users.isEmpty()) {
            modelAndView.addObject("entities", users);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("/email/{email}")
    public ModelAndView getUsersByEmail(@PathVariable String email) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = bookingFacade.getUserByEmail(email);
        if (Objects.nonNull(user)) {
            modelAndView.addObject("entities", user);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }
}

