package com.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.user.entity.ShopUser;
import com.user.service.ShopUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.user.util.SearchMybatisPlusUtil.*;


@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ShopUserService shopUserService;

    @RequestMapping("/hello")
    public String index(){
        return "hello word";
    }

  /*  @RequestMapping("/get/user")
    public User getUser(String name ){
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
    }*/


    /**
     测试  mybatis-plus 封装查询条件
     condition : 传参为 json 字符串
     * [
     {
     "column":"passwd",
     "type":"eq",
     "value":"123456"
     },
     {
     "column":"enable",
     "type":"eq",
     "value":"1"
     },
     {
     "column":"types",
     "type":"eq",
     "value":"1"
     }
     ]
     * @param conditionJson
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<ShopUser> getTestList(
            @RequestParam(name = "condition",required = false) String conditionJson) {
        QueryWrapper queryWrapper = parseWhereSql(conditionJson);
        //queryWrapper.orderByDesc("CREATE_DATE");
        return shopUserService.getPageTestList(queryWrapper);
    }



}
