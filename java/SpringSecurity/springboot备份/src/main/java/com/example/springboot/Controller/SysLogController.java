package com.example.springboot.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.Common.Result;
import com.example.springboot.Service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;
    /** 查询所有数据接口
     *
     * @return
     */
//    @GetMapping("/fiandAll")
//    public Result findAll(@RequestParam(name = "type",defaultValue = "")String type) {
//        new QueryWrapper<>()
//    }
}
