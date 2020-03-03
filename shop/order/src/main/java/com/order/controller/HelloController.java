package com.order.controller;

import com.order.entity.User;
import com.order.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * feign调用
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/orderHello")
    public String orderHello(){
       return helloService.hello();
    }

    @RequestMapping("/orderUser")
    public User orderUser(){
        return helloService.getUser("orderUser");
    }

    @RequestMapping("/user01")
    public User user01(){
        User user = new User();
        user.setName("user01");
        user.setAge(30);
        return helloService.user01(user);
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
