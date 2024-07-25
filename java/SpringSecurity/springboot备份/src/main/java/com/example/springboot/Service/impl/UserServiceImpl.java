package com.example.springboot.Service.impl;


import cn.hutool.log.Log;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.Common.Result;
import com.example.springboot.Controller.Login.UserControllerLogin;
import com.example.springboot.Mapper.MenuMapper;
import com.example.springboot.Mapper.RoleMenuMapper;
import com.example.springboot.entity.Menu;
import com.example.springboot.entity.User;
import com.example.springboot.Mapper.UserMapper;
import com.example.springboot.Service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.util.TokenUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2023-10-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log LOG = Log.get();
    @Autowired
    private UserMapper userMapper;
    /**菜单mapper接口
     *
     */
    @Autowired
    private MenuMapper menuMapper;

    /***
     * 角色菜单
     */
    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @Resource
    private StringRedisTemplate stringRedisTemplate;
//    @Override
//    public Result login(UserControllerLogin userControllerLogin) {
//        String username =userControllerLogin.getUsername();
//        String password =userControllerLogin.getPassword();
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username",username);
//        queryWrapper.eq("password",password);
//        User  userList = userMapper.selectOne(queryWrapper);
//        if (userList !=null){
//                //登录成功
//                String token= TokenUtil.getToken(userList.getId().toString(),password);
//                String  userJson = JSON.toJSONString(userList);
//                //存储toiken到redis中
//                stringRedisTemplate.opsForValue().set(token,userJson,1, TimeUnit.DAYS);
//            userControllerLogin.setToken(token);
//            userControllerLogin.setNickname(userList.getNickname());
//            userControllerLogin.setHeaderUrl(userList.getHeaderUrl());
//            Integer roleId=   userList.getRoleId();
//            if (null!=roleId){
//             List<Menu> menuList =menuMapper.selectByIdList(roleId);
//             userControllerLogin.setMenuList(menuList);
//             userControllerLogin.setRoleId(roleId);
//            }
//            return Result.success(userControllerLogin);
//
//        }else {
//            return Result.error(400,"用户名或密码出现问题");
//        }
//    }

    /***注册方法接口
     *
     * @param userControllerLogin
     * @return
     */
    @Override
    public Result register(UserControllerLogin userControllerLogin) {
        String username=userControllerLogin.getUsername();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<User> userList = userMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(userList)){
            return Result.error(500,"用户名已存在");

        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(userControllerLogin.getPassword());
        userMapper.insert(user);
        return Result.success("注册成功，将跳转到登录页面");
    }

    public List<Menu> findTreeMenu(Integer roleId){
        List<Menu> menuList = menuMapper.selectByIdList(roleId);
        //构造一级菜单
      List<Menu>  ParentList=menuList.stream().filter(menu -> menu.getId()==null).collect(Collectors.toList());
        for (Menu ParentMenu : ParentList) {
            List<Menu> childMenuList = menuList.stream()
                    .filter(menu -> menu.getPid() != null && menu.getPid().equals(ParentMenu.getId()))
                    .collect(Collectors.toList());
            // 处理子菜单逻辑...
            // 将子菜单设置到父菜单中
            ParentMenu.setChildMenuList(childMenuList);
        }
        return ParentList;
    }

/***    第一种登录和注册逻辑代码
 * 2023年完成
 */

    /***用户登录逻辑
     *
     * @param userControllerLogin
     * @return
     */
//    @Override
//    public UserControllerLogin login(UserControllerLogin userControllerLogin) {
//        User one = getUserInfo(userControllerLogin);
//        if (one != null) {
//            BeanUtil.copyProperties(one, userControllerLogin, true);
//          String token= TokenUtil.getToken(one.getId().toString(),one.getPassword());
//            userControllerLogin.setToken(token);
//            return userControllerLogin;
//        } else {
//            throw new ServiceException(Constants.CODE_600, "用户或密码错误");
//        }
//    }

    /***用户注册逻辑
     *
     * @param userControllerLogin
     * @return
     */
//    @Override
//    public UserControllerLogin register(UserControllerLogin userControllerLogin) {
//        User existingUser = getUserInfo(userControllerLogin);
//        if (existingUser != null) {
//            throw new ServiceException(Constants.CODE_600, "用户已存在");
//        } else {
//            User newUser = new User();
//            BeanUtil.copyProperties(userControllerLogin, newUser, true);
//            save(newUser);
//            return userControllerLogin;
//        }
//    }

    /*** 根据登录信息获取用户信息
     *
     * @param userControllerLogin
     * @return
     */
//    private User getUserInfo(UserControllerLogin userControllerLogin) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", userControllerLogin.getUsername());
//        queryWrapper.eq("password", userControllerLogin.getPassword());
//        User user;
//        try {
//            user = getOne(queryWrapper);
//        } catch (Exception e) {
//            LOG.error(e);
//            throw new ServiceException(Constants.CODE_500, "系统错误");
//        }
//        return user;
//    }
}

