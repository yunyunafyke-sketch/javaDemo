package com.afyke.demo.service.impl;

import com.afyke.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String saveUser(String username) {
        return username;
    }

    @Override
    public void doWork() {
        System.out.println("work");
    }
}
