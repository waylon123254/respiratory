package com.example.springboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.Common.Result;
import com.example.springboot.Mapper.MenuMapper;
import com.example.springboot.Mapper.RoleMapper;
import com.example.springboot.Mapper.RoleMenuMapper;
import com.example.springboot.Service.IRoleService;
import com.example.springboot.entity.Menu;
import com.example.springboot.entity.Role;
import com.example.springboot.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements IRoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Transactional
    public Result saveRoleMenu(Integer roleId, List<Integer> menuIds) {
        //
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleId",roleId);//可能有问题的地方
        roleMenuMapper.delete(queryWrapper);
        //保存角色菜单关联数据
       for (Integer menuId : menuIds) {
           Menu menu=menuMapper.selectById(menuId);
           Integer pid = menu.getPid();
           if (pid!=null&&!menuIds.contains(pid)){
               QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
               wrapper.eq("role_id",roleId);
               wrapper.eq("menu_id",pid);
               RoleMenu roleMenuList = roleMenuMapper.selectOne(wrapper);
               if (roleMenuList==null){
                   roleMenuList  = new RoleMenu();
                   roleMenuList.setRoleId(roleId);
                   roleMenuList.setMenuId(pid);
                   roleMenuMapper.insert(roleMenuList);

               }

           }
           RoleMenu roleMenu = new RoleMenu();
           roleMenu.setRoleId(roleId);
           roleMenu.setMenuId(menuId);
           roleMenuMapper.insert(roleMenu);
       }

        return null;
    }

    @Override
    public List<RoleMenu> selectMenuByRole(Integer roleId) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        return roleMenuMapper.selectList(queryWrapper);

    }
}
