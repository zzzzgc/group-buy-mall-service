package com.xiguo.www.group.dto;

import lombok.Data;

/**
 * @author: ZGC
 * @date Created in 2018/9/1 上午 11:35
 */
@Data
public class TestEntityVo {

    /**
     * 属性 另一边的 myProperty 通过@Mapping("theirProperty")映射到这个字段
     * <p>
     * TestEntity.myProperty  -> TestEntityVo.theirProperty
     */
    private String theirProperty = "";

    /**
     * 标题 默认的映射
     * <p>
     * TestEntity.title  -> TestEntityVo.title
     */
    private String title = "";

    /**
     * 密码(不展示)
     *
     * 直接不写 就不会映射
     */
    // String password = "";
    // private Long id;
    // private Date createAt;
    // private Date updatedAt;
}
