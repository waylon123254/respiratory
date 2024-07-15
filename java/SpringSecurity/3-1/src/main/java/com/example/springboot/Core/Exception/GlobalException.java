package com.example.springboot.Core.Exception;

import com.example.springboot.Common.ResponseEnum;
import com.example.springboot.Common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalException {
    private ResponseEnum responseEnum;
    /**
     * @ExceptionHandler相当于controller的@RequestMapping
     * 如果抛出的的是ServiceException，则调用该方法
     * @param
     * @return
     * 业务异常
     */

    /**
     * 全局异常处理
     * */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public  Result handleException(Exception e){
        log.error(e.getMessage());
        return Result.error();
    }
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public  Result handleServiceException(Exception e){
        return Result.error(500,e.getMessage());
    }

    /**
     * 自定义异常处理
     * */
//    public GlobalException(ResponseEnum responseEnum) {
//        super(responseEnum.getMessage());
//        this.responseEnum = responseEnum;
//    }
//
//    public ResponseEnum getResponseEnum() {
//        return responseEnum;
//    }
//    @ExceptionHandler(value = ServiceException.class)
//    public Result handleServiceException(ServiceException se){
//        log.error(se.getMessage());
//        return Result.error(Integer.toString(se.getCode()), se.getMessage());
//    }
}
