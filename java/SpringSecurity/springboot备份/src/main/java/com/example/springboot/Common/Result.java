package com.example.springboot.Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(ResponseEnum.SUCCESS.getCode(), "成功", null);
    }

    public static <T> Result<T> success(Integer code, String msg, T data) {
        return new Result<>(code, msg, data);
    }
    public static <T> Result<T> success(Integer code, String msg) {
        return new Result<>(code, msg);
    }
    public static <T> Result<T> success(T data) {
        return new Result<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), data);
    }
//    public static <T> Result<T> success(Integer code,String msg) {
//        return new Result<>(code,msg);
//    }
    public static <T> Result<T> deleteSuccess() {
        return new Result<>(ResponseEnum.NO_CONTENT.getCode(), ResponseEnum.NO_CONTENT.getMessage(), null);
    }

    public static <T> Result<T> deleteSuccess(T data) {
        return new Result<>(ResponseEnum.NO_CONTENT.getCode(), ResponseEnum.NO_CONTENT.getMessage(), data);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResponseEnum.INTERNAL_SERVER_ERROR.getCode(), ResponseEnum.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
