package com.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.order.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ribbon调用服务
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/order/hello")
    public String orderHello(){
        //return restTemplate.getForEntity("http://USER-SERVICE/hello", String.class).getBody();
        return restTemplate.getForObject("http://USER-SERVICE/hello", String.class);
    }

    @RequestMapping("/order/user")
    @HystrixCommand(fallbackMethod = "getError")
    public User getUser(){
        return restTemplate.getForObject("http://USER-SERVICE/get/user?name={name}",
                User.class, "ordername");
    }

    public User getError(){
        User user = new User();
        user.setAge(0);
        user.setName("");
        return user;
    }

}
