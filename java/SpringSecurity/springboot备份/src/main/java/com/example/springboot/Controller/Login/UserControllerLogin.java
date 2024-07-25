package com.example.springboot.Controller.Login;

import com.example.springboot.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class UserControllerLogin {
    private  Integer id;
    private  Integer roleId;
    private  String username;
    private  String password;
    private  String nickname;
    private  String headerUrl;
    private  String avatar;
    private  String token;
    private List<Menu> menuList;
}
