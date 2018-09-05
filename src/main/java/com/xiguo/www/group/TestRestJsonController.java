package com.xiguo.www.group;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZGC
 * @date Created in 下午 8:49 2018/8/24
 */
@RestController
public class TestRestJsonController {


    @RequestMapping("/hello")
    public Map index() {
        HashMap objectHashMap = new HashMap<Object, Object>(5);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("123456");
        strings.add("658798");
        strings.add("786");
        strings.add("78");
        objectHashMap.put("Test", strings);
        int[] asd = {123, 123456, 789, 9};
        objectHashMap.put("Test", strings);
        objectHashMap.put("tes", asd);
        return objectHashMap;
    }
}
