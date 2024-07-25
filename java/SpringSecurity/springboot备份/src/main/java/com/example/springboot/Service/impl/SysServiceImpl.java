package com.example.springboot.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.Mapper.SysLogMapper;
import com.example.springboot.Service.ISysLogService;
import com.example.springboot.entity.SysLog;
import org.springframework.stereotype.Service;

@Service
public class SysServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
}
