package com.epam.training.service.user;

import com.epam.training.dao.user.UserDao;
import com.epam.training.exception.NoEntityException;
import com.epam.training.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(Long userId) {
        User user = userDao.findById(userId).orElseThrow(() -> new NoEntityException("User not found"));
        log.info("User found: {}", user.toString());
        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        for (User user : userDao.findAll()) {
            if (user.getEmail().equals(email)) {
                log.info("User found: {}", user.toString());
                return user;
            }
        }
        log.error("User not found.");
        throw new IllegalStateException();
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        List<User> foundUsers = new ArrayList<>();

        for (User user : userDao.findAll()) {
            if (user.getName().contains(name)) {
                foundUsers.add(user);
            }
        }

        log.info(String.format("%s user(s) found: ", foundUsers.size()));

        return foundUsers;
    }

    @Override
    public User createUser(User user) {
        long id = new Random().nextLong();
        if (isExist(id)) {
            createUser(user);
        } else {
            user.setId(id);
        }
        if (!isMailFree(user)) {
            log.error("User not created because of provided email address already used.");
            throw new IllegalStateException();
        }

        User newUser = userDao.save(user);
        log.info("User created successfully. User details:{} ", user.toString());
        return newUser;
    }

    @Override
    public User updateUser(User user) {
        User currentUser = userDao.findById(user.getId()).get();
        userDao.delete(currentUser);
        return userDao.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userDao.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    boolean isExist(Long id) {
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private boolean isMailFree(User user) {

        boolean isMailFree = true;

        for (User storedUser : userDao.findAll()) {
            if (storedUser.getId() != user.getId() && storedUser.getEmail().equals(user.getEmail()))
                isMailFree = false;
        }

        return isMailFree;
    }
}
