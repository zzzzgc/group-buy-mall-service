package com.xiguo.www.group.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author: ZGC
 * @date Created in 2018/8/27 下午 6:42
 */
public class JsonKit {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T toObject(String jsonString, Class<T> zlass) throws IOException {
        return objectMapper.readValue(jsonString, zlass);
    }
}
