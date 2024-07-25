package com.example.springboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.Mapper.MenuMapper;
import com.example.springboot.Service.IMenuService;
import com.example.springboot.entity.Menu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Override
    public List<Menu> findAll(String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort_num");
        if (StringUtils.isNotEmpty(name)){
            queryWrapper.like("name",name);
        }
        List<Menu> MenuList = this.baseMapper.selectList(queryWrapper);//menu全部菜单信息
       //构建一级菜单
        List<Menu> ParentMenuList=MenuList.stream().filter(menu -> menu.getPid()==null).collect(Collectors.toList());
        for (Menu ParentMenu : ParentMenuList) {
            List<Menu> childMenuList = MenuList.stream()
                    .filter(menu -> menu.getPid() != null && menu.getPid().equals(ParentMenu.getId()))
                    .collect(Collectors.toList());
            // 处理子菜单逻辑...
            // 将子菜单设置到父菜单中
            ParentMenu.setChildMenuList(childMenuList);
        }
        return ParentMenuList;
    }
}
