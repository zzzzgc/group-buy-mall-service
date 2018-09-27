package com.xiguo.www.group.enums;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author: ZGC
 * @date Created in 2018/9/3 上午 10:30
 */
public class RETemplate {

    public static ResponseEntity ok() {
        return new ResponseEntity(HttpStatus.OK);
    }

    public static ResponseEntity ok(Object data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    public static ResponseEntity failure() {
        return new ResponseEntity<>("服务器异常,请稍后重试.或联系开发人员手机: 18718840426", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity failure(String msg) {
        return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity reject(String msg) {
        return new ResponseEntity<>(msg, HttpStatus.NOT_ACCEPTABLE);
    }


}
