package com.xiguo.www.group.enums;

/**
 * API访问响应实体的状态枚举
 * @author: ZGC
 * @date Created in 2018/8/27 下午 6:12
 */
public enum ResponseStatus {
    SUCCESS(0, "请求成功"),
    WARN(-1, "网络异常，请稍后重试");

    private int code;
    private String msg;

    ResponseStatus(int code, String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
