package com.example.springboot.Controller.Ecahrt;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.example.springboot.Common.Result;
import com.example.springboot.Service.IUserService;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/example")
    public Result get(){
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y", CollUtil.newArrayList("800", "230", "224", "218", "135"," 147"," 260"));
        return  Result.success(map);

    }

    @GetMapping("/members")
    public Result members(){
        List<User> list = iUserService.list();
        int q1 = 0, q2 = 0, q3 = 0,q4=0;
        for (User user : list) {
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter) {
                // cases and statements
                case Q1:q1+=1;break;
                case Q2:q2+=1;break;
                case Q3:q3+=1;break;
                case Q4:q4+=1;break;
                default:break;
            }
        }
        Map<String, Integer> data = new HashMap<>();
        data.put("Q1", q1);
        data.put("Q2", q2);
        data.put("Q3", q3);
        data.put("Q4", q4);
        return Result.success(data);
    }
}
