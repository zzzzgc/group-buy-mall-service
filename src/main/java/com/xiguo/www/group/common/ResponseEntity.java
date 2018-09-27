package com.xiguo.www.group.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiguo.www.group.enums.ResponseStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * API访问响应实体
 *
 * @author: ZGC
 * @date Created in 2018/8/27 下午 6:08
 */
@Data
public class ResponseEntity implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public ResponseEntity(ResponseStatus responseStatus) {
        this.code = responseStatus.getCode();
        this.msg = responseStatus.getMsg();
    }

    public ResponseEntity(ResponseStatus responseStatus, Object data) {
        this(responseStatus);
        this.data = data;
    }

    public ResponseEntity(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseEntity(String msg) {
        this.code = 3;
        this.msg = msg;
    }
}
