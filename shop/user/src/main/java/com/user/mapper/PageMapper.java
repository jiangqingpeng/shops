package com.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.user.entity.ShopUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PageMapper extends BaseMapper<ShopUser> {
    /**
     * 自定义sql分页
     * @param page
     * @param queryWrapper 看这里看这里，如果自定义的方法中需要用到wrapper查询条件，需要这样写
     * @return
     */
    IPage<ShopUser> selectMyPage(IPage<ShopUser> page,
                                 @Param(Constants.WRAPPER) Wrapper<ShopUser> queryWrapper);

    IPage<ShopUser> getPage(IPage<ShopUser> page,
                                 @Param(Constants.WRAPPER) Wrapper queryWrapper);

}
