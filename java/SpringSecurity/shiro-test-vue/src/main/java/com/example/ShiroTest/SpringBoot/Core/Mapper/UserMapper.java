package com.example.ShiroTest.SpringBoot.Core.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ShiroTest.SpringBoot.Core.Entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: 吕宏博
 * @Date: 2024--03--27--9:56
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
