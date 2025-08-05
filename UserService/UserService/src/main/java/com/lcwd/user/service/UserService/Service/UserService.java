package com.lcwd.user.service.UserService.Service;

import com.lcwd.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {

    //create
    User saveUser(User user);

    //getAll
    List<User> getAllUser();

    //getById
    User getUser(String userId);
}
