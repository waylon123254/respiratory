package cn.hxh.files.mapper;

import cn.hxh.files.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-21 13:46:01
 */
@Mapper
@Repository
public interface UserDao {

    User query(String username, String password);
}