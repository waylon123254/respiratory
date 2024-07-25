package com.example.springboot.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Menu;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author lv
 * @since 2024-01-17
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> findAll(String name);
}
