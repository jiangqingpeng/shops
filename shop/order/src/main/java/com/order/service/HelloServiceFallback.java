package com.order.service;

import com.order.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return null;
    }

    @Override
    public User getUser(String name) {
        User user = new User();
        user.setAge(0);
        user.setName("");
        return user;
    }

    @Override
    public User user01(User user) {
        return null;
    }
}
