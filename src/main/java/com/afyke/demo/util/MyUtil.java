package com.afyke.demo.util;

import com.afyke.demo.config.SpringContextHolder;
import com.afyke.demo.service.UserService;

public class MyUtil {

    public static void doSomething() {
        UserService userService = SpringContextHolder.getBean(UserService.class);
        userService.doWork();
    }
}
