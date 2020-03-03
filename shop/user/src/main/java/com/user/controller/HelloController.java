package com.user.controller;

import com.user.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    public String index(){
        ServiceInstance serviceInstance = client.getLocalServiceInstance();
        logger.info("/hello, host: " + serviceInstance.getHost() +"； " +
                "serviceId :" + serviceInstance.getServiceId());
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
