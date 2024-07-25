package com.example.springboot.Exception;


import com.example.springboot.Common.ResponseEnum;
import lombok.Data;

/***
 * 自定义异常
 * */
@Data
public class ServiceException extends RuntimeException{
    private Integer code;
    public  ServiceException(Integer code,String msg){
        super(msg);
        this.code=code;
    }





}
