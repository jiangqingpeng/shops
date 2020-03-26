package com.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.user.entity.ConditionVo;
import com.user.entity.ShopUser;
import com.user.mapper.PageMapper;
import com.user.util.SearchMybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {

    @Autowired
    private PageMapper pageMapper;

    public IPage<ShopUser> get( int pageNum, int pageSize ){
        QueryWrapper<ShopUser> wrapper = new QueryWrapper();
        //wrapper.like("name", "雨").lt("age", 40);
        Page<ShopUser> page = new Page<>(pageNum, pageSize);
        IPage<ShopUser> mapIPage = pageMapper.selectMyPage(page, wrapper);
        System.out.println( "行数：" + mapIPage.getTotal() );
        System.out.println("总页数： "+mapIPage.getPages()) ;
        return mapIPage;
    }


    public IPage<ShopUser> getUser( int pageNum, int pageSize, String condition ){
        QueryWrapper queryWrapper = SearchMybatisPlusUtil.parseWhereSql( condition );
        Page<ShopUser> page = new Page<>(pageNum, pageSize);
        IPage<ShopUser> mapIPage = pageMapper.getPage(page, queryWrapper);
        System.out.println( "行数：" + mapIPage.getTotal() );
        System.out.println("总页数： "+mapIPage.getPages()) ;
        return mapIPage;
    }


    public IPage<ShopUser> getUserList( ShopUser shopUser ) throws Exception{
        List<ConditionVo> list = SearchMybatisPlusUtil.sqlWhereDo( shopUser );
        QueryWrapper queryWrapper = SearchMybatisPlusUtil.parseWhereSqlList( list );

        Page<ShopUser> page = new Page<>(1, 50);
        IPage<ShopUser> mapIPage = pageMapper.getPage(page, queryWrapper);
        System.out.println( "行数：" + mapIPage.getTotal() );
        System.out.println("总页数： "+mapIPage.getPages()) ;

        return mapIPage;
    }


}
