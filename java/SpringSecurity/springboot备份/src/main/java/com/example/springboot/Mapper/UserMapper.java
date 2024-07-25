package com.example.springboot.Mapper;

import com.example.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lv
 * @since 2023-10-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
