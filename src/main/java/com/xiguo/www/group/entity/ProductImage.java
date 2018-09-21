package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"product"})
@EqualsAndHashCode(callSuper = true, exclude = {"product"})
@Getter
@Setter

@Data
@Entity
@Table(appliesTo = "product_image", comment = "团购商品图片表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updateAt"})
public class ProductImage extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, columnDefinition = "varchar(250) COMMENT '图片地址' ")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;
}
