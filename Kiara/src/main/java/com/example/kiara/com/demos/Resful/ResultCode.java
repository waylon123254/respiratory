package com.example.kiara.com.demos.Resful;

/**
 * @Auther: 吕宏博
 * @Date: 2024--07--17--10:41
 * @Description:
 */

public enum ResultCode {
    SUCCESS(200, "请求成功，服务器成功返回请求的数据。"),
    CREATED(201, "请求成功，并且服务器创建了新的资源。"),
    NO_CONTENT(204, "请求成功，但不需要返回任何内容。通常用于删除操作。"),
    BAD_REQUEST(400, "请求无效，服务器无法理解该请求。通常是客户端参数错误。"),
    UNAUTHORIZED(401, "请求未经授权，需要用户身份验证。"),
    FORBIDDEN(403, "服务器理解请求，但拒绝执行。通常是因为用户权限不足。"),
    NOT_FOUND(404, "请求的资源不存在。"),
    METHOD_NOT_ALLOWED(405, "请求的HTTP方法不被允许。"),
    CONFLICT(409, "请求的资源与服务器上的资源发生冲突。"),
    GONE(410, "请求的资源已被永久删除，并且不会再可用。"),
    UNSUPPORTED_MEDIA_TYPE(415, "请求的格式不受支持。"),
    UNPROCESSABLE_ENTITY(422, "请求的实体格式正确，但语义错误。"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误，无法完成请求。"),
    NOT_IMPLEMENTED(501, "服务器不支持当前请求的功能。"),
    SERVICE_UNAVAILABLE(503, "服务器当前不可用，可能是过载或维护。");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
