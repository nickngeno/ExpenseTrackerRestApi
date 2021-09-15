package com.kimmy.ExpenseTrackerREStAPI.service;

import com.kimmy.ExpenseTrackerREStAPI.entity.User;

import java.util.Map;

public interface UserService {

    User addUser(User user);
    Map<String, String> login (User user);
}
