package com.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TestMapper extends BaseMapper<User> {
}
