package com.example.springboot.Service;

import com.example.springboot.Common.Result;
import com.example.springboot.Controller.Login.UserControllerLogin;
import com.example.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2023-10-22
 */
public interface IUserService extends IService<User> {
    /**第二种方式
     *
     * @param userControllerLogin
     * @return
     */
   // Result login(UserControllerLogin userControllerLogin);

    Result register(UserControllerLogin userControllerLogin);


    /***登录和注册逻辑代码
    *
    */
//   UserControllerLogin login(UserControllerLogin userControllerLogin);
//
//   UserControllerLogin register(UserControllerLogin userControllerLogin);
}
