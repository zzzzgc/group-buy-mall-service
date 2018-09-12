package com.xiguo.www.group.utils;

import java.util.UUID;

/**
 * @author: ZGC
 * @date Created in 2018/9/8 下午 6:13
 */
public class uuidKit {
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
