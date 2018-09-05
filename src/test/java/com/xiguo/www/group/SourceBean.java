package com.xiguo.www.group;

import org.dozer.Mapping;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 7:55
 */
public class SourceBean {
    private Long id;

    private String name;

    @Mapping("binaryData")
    private String data;

    @Mapping("pk")
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
