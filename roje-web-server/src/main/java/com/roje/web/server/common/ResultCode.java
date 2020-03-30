package com.roje.web.server.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Ro
 */
public enum ResultCode {
    /** 成功 */
    SUCCESS(0, "成功"),

    //通用错误码
    /** 未知错误 */
    ERROR(1001, "未知错误"),
    /** 请求未授权 */
    UNAUTHORIZED(1002, "请求未授权"),
    /** 会话过期错误码 */
    SESSION_EXPIRED(1003, "会话过期"),
    /** 会话超时错误码 */
    SESSION_TIMEOUT(1004, "会话超时"),
    //登录错误码
    /** 用户名或密码错误 */
    USERNAME_OR_PSW_ERROR(5001,"用户名或密码错误"),
    CAPTCHA_INVALID(5002, "无效的验证码"),
    ;

    @JsonValue
    public final int code;

    public final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
