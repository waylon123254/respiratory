package com.example.springboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_log")
public class SysLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String  username;
    private String record;
    private String type;
    private Date createTime;
}
