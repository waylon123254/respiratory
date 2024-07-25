package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
;import java.io.Serializable;

@Data
@TableName("sys_role_menu")
@ApiModel(value = "SysMenu对象", description = "系统菜单表")
@ToString
public class RoleMenu implements Serializable {
     private static final long serialVersionUID = 1L;
     @TableField(value = "roleId")
     @ApiModelProperty("角色id")
     private Integer roleId;

     @TableField(value = "menuId")
     @ApiModelProperty("菜单id")
     private Integer menuId;

}
