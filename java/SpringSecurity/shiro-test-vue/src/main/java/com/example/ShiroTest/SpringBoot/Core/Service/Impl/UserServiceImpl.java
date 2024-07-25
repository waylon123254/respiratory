package com.example.ShiroTest.SpringBoot.Core.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ShiroTest.SpringBoot.Core.Entity.User;
import com.example.ShiroTest.SpringBoot.Core.Mapper.UserMapper;
import com.example.ShiroTest.SpringBoot.Core.Service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * @Auther: 吕宏博
 * @Date: 2024--03--27--10:17
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public void checkLogin(String username,String password)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
    }

}
