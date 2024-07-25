package com.example.springboot.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.Common.Result;
import com.example.springboot.Service.IRoleService;
import com.example.springboot.Service.IUserService;
import com.example.springboot.entity.Role;
import com.example.springboot.entity.RoleMenu;
import com.example.springboot.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;
    /**
     * 新增/修改
     * role角色信息
     * */
    @PostMapping("/save")
    public Result save(@RequestBody Role role) {
        //角色名重复问题
       boolean  b= roleService.saveOrUpdate(role);
       if (b){
           return Result.success(200,"保存角色成功",role); // 使用无参的 success() 方法
       }else
       {
           return  Result.error();
       }
    }

    /**查询接口
     * 查询菜单所有
     * @return
     */
    @PostMapping("/findAll")
    public Result findAll(){
        return Result.success(200,"查询成功",roleService.list());
    }

    /***删除接口，根据id进行删除
     *单个用户删除
     * @param id
     * @return
     * 创建一个名为 queryWrapper 的 QueryWrapper 对象，用于构建查询条件。
     * 使用 queryWrapper.eq("id", id) 方法设置查询条件，即根据 id 字段等于传入的 id 值进行查询。
     * 调用 userService.list(queryWrapper) 方法执行查询，并将结果存储在 list 变量中。
     * 如果查询结果为空列表（即没有找到匹配的记录），则返回一个带有错误信息的 Result 对象，表示角色信息已经被使用，无法删除。
     * 调用 roleService.removeById(id) 方法删除指定的角色信息。如果删除成功，返回一个带有成功信息的 Result 对象。
     * 如果删除失败，则返回一个默认的错误 Result 对象。
     */
    @PostMapping("/deleteById/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        List<User> list=userService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)){
            return Result.error(500,"角色信息已经被使用，无法删除");
        }

        boolean delete = roleService.removeById(id);
        if(delete)
        {
            return Result.success(200,"角色信息删除成功");
        } else
        {
            return Result.error(405,"角色信息删除失败");
        }
    }
    /***
    *id批量删除角色
    * **/
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> idList) {
        int successCount = 0; // 统计删除成功的条数
        for (Integer id : idList) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            List<User> list=userService.list(queryWrapper);
            if (CollectionUtils.isEmpty(list)){
                return Result.error(500,"菜单信息已经被使用，无法删除");
            }
        }
        boolean delete = roleService.removeByIds(idList);
        if(delete)
        {
            successCount = idList.size(); // 设置成功删除的条数为传递的id列表的长度
            return Result.success(200, "删除成功",successCount);
        } else
        {
            return Result.error(405, "删除失败");
        }
    }

    /**分页接口
     * 菜单数据分页接口
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/findPage")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(name = "name",defaultValue = "") String name
                       ) {
        Page<Role> page = new Page(pageNum,pageSize);
        QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }
        IPage<Role> rolePage = roleService.page(page, queryWrapper);
        return Result.success(200,"角色信息查询成功",rolePage);
    }

    /***角色分配菜单
     * @param roleId
     * @param menuIds
     */
    @PostMapping("/saveRoleMenu/{roleId}")
    public Result SaveRoleMenu(@PathVariable(name = "roleId") Integer roleId,
                               @RequestBody List<Integer> menuIds)
     {
        return roleService.saveRoleMenu(roleId,menuIds);
    }
    /**角色分配菜单接口
     * @param  roleId
     * @return
     */
    @PostMapping("/selectMenuByRole/{roleId}")
    public Result selectMenuByRole(@PathVariable(name = "roleId") Integer roleId){
      List<RoleMenu> roleMenuList= roleService.selectMenuByRole(roleId);
        List<Integer> menuIdList= roleMenuList.stream().map(roleMenu -> roleMenu.getMenuId()).collect(Collectors.toList());
        return Result.success(menuIdList);
    }

}
