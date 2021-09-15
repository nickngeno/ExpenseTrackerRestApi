package com.kimmy.ExpenseTrackerREStAPI.controller;


import com.kimmy.ExpenseTrackerREStAPI.entity.User;
import com.kimmy.ExpenseTrackerREStAPI.service.UserService;
import com.kimmy.ExpenseTrackerREStAPI.service.serviceImpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        User user1 =  userService.addUser(user);
        Map<String, String> response = new HashMap<>();
        response.put("userId",user1.getUserId().toString());
        response.put("name",user1.getName());
        response.put("email",user1.getEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/")
    public String user() {
        return "Nicholas was here";
    }
}
