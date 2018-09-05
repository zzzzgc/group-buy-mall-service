package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.dozer.Mapping;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: ZGC
 * @date Created in 2018/9/1 上午 11:49
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter

@Entity
@Table(appliesTo = "test_entity", comment = "表注释")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"}, allowGetters = true)
public class TestEntity extends BaseEntity  implements Serializable {

    /**
     * 属性
     */
//    @Mapping("theirProperty")
    @Column(nullable = false, columnDefinition = "varchar(25) COMMENT '参数' ")
    private String myProperty = "";

    /**
     * 标题
     */
    @Column(nullable = false, columnDefinition = "varchar(25) COMMENT '标题' ")
    private String title = "";

    /**
     * 密码
     */
    @Column(nullable = false, columnDefinition = "varchar(25) COMMENT '密码' ")
    private String password = "";


    // 本案列.省略getting setting方法.......
}
