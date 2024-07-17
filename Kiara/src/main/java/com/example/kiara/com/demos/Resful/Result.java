package com.example.kiara.com.demos.Resful;

import lombok.Data;

/**
 * @Auther: 吕宏博
 * @Date: 2024--07--17--10:41
 * @Description:
 */
@Data
public class Result<T>{
    private int code;
    private String message;
    private T data;


    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // success methods
    public static <T> Result<T> success(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), "操作成功", data);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS.getCode(), "操作成功", null);
    }

    // fail methods
    public static <T> Result<T> fail(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> fail(String message, T data) {
        return new Result<>(ResultCode.BAD_REQUEST.getCode(), message, data);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(ResultCode.BAD_REQUEST.getCode(), "操作失败", data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.BAD_REQUEST.getCode(), "操作失败", null);
    }

    // error methods
    public static <T> Result<T> error(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> error(String message, T data) {
        return new Result<>(ResultCode.INTERNAL_SERVER_ERROR.getCode(), message, data);
    }

    public static <T> Result<T> error(T data) {
        return new Result<>(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "服务器内部错误", data);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "服务器内部错误", null);
    }
}
