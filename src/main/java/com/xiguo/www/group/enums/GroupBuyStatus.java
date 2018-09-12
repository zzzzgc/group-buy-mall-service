package com.xiguo.www.group.enums;

/**
 * 团购状态枚举类
 * @author: ZGC
 * @date Created in 2018/9/12 下午 1:12
 */
public enum GroupBuyStatus {
    NOT_START(0, "未开始"),
    START(1, "进行中"),
    END(2, "已结束"),
    DELETE(3, "已删除"),;

    private int status;
    private String msg;

    GroupBuyStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
