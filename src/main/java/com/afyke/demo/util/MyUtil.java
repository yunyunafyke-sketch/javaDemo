package com.afyke.demo.util;

import com.afyke.demo.config.SpringContextHolder;
import com.afyke.demo.service.impl.UserServiceImpl;

public class MyUtil {

    public static void doSomething() {
        UserServiceImpl userServiceImpl = SpringContextHolder.getBean(UserServiceImpl.class);
        userServiceImpl.doWork();
    }
}
