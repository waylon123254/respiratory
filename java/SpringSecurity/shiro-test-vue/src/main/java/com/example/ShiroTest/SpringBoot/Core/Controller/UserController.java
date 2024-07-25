package com.example.ShiroTest.SpringBoot.Core.Controller;

import com.example.ShiroTest.SpringBoot.Core.Service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Auther: 吕宏博
 * @Date: 2024--03--27--14:41
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @PostMapping("/login")
    public  String login(String username,String password) {
        try {
            userService.checkLogin(username,password);
            System.out.println("登录成功");
            return "test";
        }
        catch (Exception e){
            System.out.println("登录失败");
            return "login";
        }
    }
}
