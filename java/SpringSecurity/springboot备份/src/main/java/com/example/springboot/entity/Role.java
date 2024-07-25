package com.example.springboot.entity;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@TableName("role")
@Data
@ApiModel(value = "Role对象", description = "角色信息")
@ToString
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("角色ID")
    private Integer id;

        @TableField(value = "name")
//    @TableField(value = "name", exist = true)
    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("角色唯一标识")
    private String flag;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private Date createTime;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time")
    private Date updateTime;

    @ApiModelProperty("是否已删除")
    @TableField(value = "is_deleted")
    private Integer isDeleted;

}