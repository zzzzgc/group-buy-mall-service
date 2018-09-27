package com.xiguo.www.group.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据库实体基础类
 * <p>
 * 这不是一个完整的实体类且映射到数据库表
 * 但是它的属性都将映射到其子类的数据库字段
 * 因为@MappedSuperclass
 *
 * @author: ZGC
 * @date Created in 2018/8/30 上午 11:22
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected Date createAt;
    @UpdateTimestamp
    @Column(nullable = false)
    protected Date updateAt;
}
