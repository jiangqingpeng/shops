package com.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.user.annotion.Fields;
import com.user.entity.ConditionVo;
import com.user.entity.ShopUser;
import com.user.service.PageService;
import com.user.util.SearchMybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class PageController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PageService pageService;

    /**
     * mybatis-plus 的 分页，不包含自定义查询封装
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getPageList", method = RequestMethod.POST)
    public IPage<ShopUser> getPageList(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return pageService.get( pageNum, pageSize );
    }

    /**
     测试  mybatis-plus 的分页 以及 封装查询条件
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
     * @param condition
     * @return
     */
    @RequestMapping(value = "/getPageList11", method = RequestMethod.POST)
    public IPage<ShopUser> getPageList11(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "condition",required = false) String condition) {
        return pageService.getUser( pageNum, pageSize, condition );
    }



    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public IPage<ShopUser> getUserList( @RequestBody(required = false) ShopUser shopUser ) throws Exception {
        return pageService.getUserList(shopUser);
    }



   /* public static void main(String[] args) throws Exception {

        *//*ShopUser shopUser = new ShopUser();
        shopUser.setName("jqp");
        shopUser.setTypes(0);
        shopUser.setPhone("1359024");
        List<ConditionVo> list = SearchMybatisPlusUtil.sqlWhereDo( shopUser );
        QueryWrapper queryWrapper = SearchMybatisPlusUtil.parseWhereSqlList( list );*//*



        *//*ShopUser user = new ShopUser();
        // 拿到该类
        Class<?> clz = user.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clz.getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            Field f = clz.getDeclaredField(field.getName());
            Fields anno = f.getAnnotation(Fields.class);
            if( anno == null || StringUtils.isBlank(anno.value()) ){
                System.out.println(" null ");
            }else {
                map.put(field.getName(), anno.value());
            }
        }
        System.out.println( map.size() );*//*

        //获取User类中 private String userName; 变量上的注解 @Field
        *//*Field f = user.getClass().getDeclaredField("name");
        Fields anno = f.getAnnotation(Fields.class);
        user.setName(anno.value());
        System.out.println(user.getName());*//*

    }*/


}
