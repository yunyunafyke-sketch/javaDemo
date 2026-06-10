package com.afyke.demo.controller;

import com.afyke.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/save")
    public String save() {
        return userService.saveUser("张三");
    }
}
