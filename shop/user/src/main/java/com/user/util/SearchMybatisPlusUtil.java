package com.user.util;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.user.annotion.Fields;
import com.user.entity.ConditionVo;
import com.user.entity.ShopUser;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装 mybatis-plus 的查询条件
 */
public class SearchMybatisPlusUtil {

    public static QueryWrapper parseWhereSql(String conditionJson) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty(conditionJson)) {
            List<ConditionVo> conditionList = JSON.parseArray(conditionJson, ConditionVo.class);
            if (CollectionUtils.isNotEmpty(conditionList)) {
                for (ConditionVo conditionVo : conditionList) {
                    switch (conditionVo.getType()) {
                        //等于
                        case "eq":
                            queryWrapper.eq(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        //不等于
                        case "ne":
                            queryWrapper.ne(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        //模糊查询，like '%value%'
                        case "like":
                            queryWrapper.like(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        //模糊查询，like '%value'
                        case "likeleft":
                            queryWrapper.likeLeft(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        //模糊查询，like 'value%'
                        case "likeright":
                            queryWrapper.likeRight(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        //模糊查询，not like '%value%'
                        case "notlike":
                            queryWrapper.notLike(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        //大于
                        case "gt":
                            queryWrapper.gt(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        //小于
                        case "lt":
                            queryWrapper.lt(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        //大于等于
                        case "ge":
                            queryWrapper.ge(conditionVo.getColumn(), conditionVo.getValue());
                            break;
                        //小于等于
                        case "le":
                            queryWrapper.le(conditionVo.getColumn(), conditionVo.getValue());
                            break;

                        //TODO  between 和 in 需要特殊处理，这里暂未特殊处理
                        //between value1 and value2
                        /*case "between":
                            queryWrapper.between(conditionVo.getColumn(), conditionVo.getValue(), conditionVo.getValue());
                            break;
                        //in  传多个值（一个一个传），或者传 list 集合
                        case "in":
                            queryWrapper.in(conditionVo.getColumn(), conditionVo.getValue(), conditionVo.getValue());
                            queryWrapper.in(conditionVo.getColumn(), conditionList);
                            break;*/
                    }
                }
            }
        }
        return queryWrapper;
    }


    public static QueryWrapper parseWhereSqlList(List<ConditionVo> list) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ConditionVo conditionVo : list) {
                switch (conditionVo.getType()) {
                    //等于
                    case "eq":
                        queryWrapper.eq(conditionVo.getColumn(), conditionVo.getValue());
                        break;
                    //不等于
                    case "ne":
                        queryWrapper.ne(conditionVo.getColumn(), conditionVo.getValue());
                        break;
                    //模糊查询，like '%value%'
                    case "like":
                        queryWrapper.like(conditionVo.getColumn(), conditionVo.getValue());
                        break;
                    //模糊查询，like '%value'
                    case "likeleft":
                        queryWrapper.likeLeft(conditionVo.getColumn(), conditionVo.getValue());
                        break;
                    //模糊查询，like 'value%'
                    case "likeright":
                        queryWrapper.likeRight(conditionVo.getColumn(), conditionVo.getValue());
                        break;
                    //模糊查询，not like '%value%'
                    case "notlike":
                        queryWrapper.notLike(conditionVo.getColumn(), conditionVo.getValue());
                        break;
                    //大于
                    case "gt":
                        queryWrapper.gt(conditionVo.getColumn(), conditionVo.getValue());
                        break;
                    //小于
                    case "lt":
                        queryWrapper.lt(conditionVo.getColumn(), conditionVo.getValue());
                        break;
                    //大于等于
                    case "ge":
                        queryWrapper.ge(conditionVo.getColumn(), conditionVo.getValue());
                        break;
                    //小于等于
                    case "le":
                        queryWrapper.le(conditionVo.getColumn(), conditionVo.getValue());
                        break;
                }
            }
        }
        return queryWrapper;
    }



    //object:前端传值对象
    public static List<ConditionVo> sqlWhereDo(Object object) throws Exception {
        List<ConditionVo> list = new ArrayList<>();

        // 拿到该类
        Class<?> clz = object.getClass();

        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {

            //为私有属性放行
            field.setAccessible(true);

            //field.getName() 为属性名，例如：id，name等
            Field f = clz.getDeclaredField(field.getName());
            Fields anno = f.getAnnotation(Fields.class);
            if (anno != null && StringUtils.isNotBlank(anno.value())) {
                ConditionVo conditionVo = new ConditionVo();

                //获取属性Fields注解的值，是eq，like等
                String value = anno.value().toString();
                conditionVo.setType(value);

                conditionVo.setColumn(field.getName());

                //获取该属性的值（由前端传过来的值）
                Object objValue = field.get(object);
                if( objValue != null ){
                    conditionVo.setValue(objValue.toString());
                    list.add( conditionVo );
                }
            }
        }
        return list;
    }

}
