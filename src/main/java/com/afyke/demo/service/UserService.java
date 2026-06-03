package com.afyke.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private OrderService orderService;
    public void doWork() {
        System.out.println("work");
    }
}
