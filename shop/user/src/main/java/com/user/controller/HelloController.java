package com.user.controller;

import com.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello")
    public String index(){
        return "hello word";
    }

    @RequestMapping("/get/user")
    public User getUser( String name ){
        logger.info("参数为：" + name);
        User user = new User();
        user.setAge(25);
        user.setName("zhangsan");
        return user;
    }

    @RequestMapping("/get/user01")
    public User getUser01(@RequestBody User user ){
        logger.info("参数为：" + user.getName()+", " +user.getAge());
        return user;
    }

}
