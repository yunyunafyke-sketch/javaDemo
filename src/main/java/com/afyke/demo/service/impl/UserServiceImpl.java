package com.afyke.demo.service.impl;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements com.afyke.demo.service.UserService {
    public void doWork() {
        System.out.println("work");
    }
}
