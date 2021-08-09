package com.epam.training.service.user;

import com.epam.training.model.User;

import java.util.List;

public interface UserService {

    User getUserById(Long userId);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name, int pageSize, int pageNum);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long userId);

    List<User> findAll();

}
