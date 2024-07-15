package com.example.springboot.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author lv
 * @since 2023-10-22
 */
@Getter
@Setter
@ApiModel(value = "User对象", description = "User方法")
@TableName("user1")
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("角色")
    @TableField(value = "role")
    private  String role;

    @ApiModelProperty("角色id")
    @TableField(value = "role_id")
    private  Integer roleId;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("电子邮箱")
    private String email;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("昵称")
    private String nickname;

    @TableField("createTime")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @TableField(exist = false)//表示该属性不为数据库表字段，但又是必须使用的
    private String headerUrl;
}
