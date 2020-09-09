package com.zmn.census.common.core.result;

/**
 * 枚举了一些常用API操作码
 * Created by zhaozhirong on 2019/11/25.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_PARAM_FAILED(90003, "缺少必填参数"),
    PARAM_EFFECTIVE_FAILED(90004, "参数有效性校验"),
    UNAUTHORIZED(20011, "登陆已过期,请重新登陆"),
    PATH_ERROR(501, "请求路径不存在"),
    FORBIDDEN(403, "权限不足");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}