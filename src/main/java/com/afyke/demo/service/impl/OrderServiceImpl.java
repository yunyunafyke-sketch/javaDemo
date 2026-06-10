package com.afyke.demo.service.impl;

import com.afyke.demo.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    public void OrderNumber() {
        System.out.println("OrderNumber");
    }
}
