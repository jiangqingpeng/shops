package com.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.user.entity.ShopUser;
import com.user.mapper.ShopUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShopUserService {

    @Autowired
    private ShopUserMapper mapper;

    public List<ShopUser> getPageTestList(QueryWrapper queryWrapper) {
        List<ShopUser> list = mapper.getPageTestList(queryWrapper);
        return list;
    }

}
