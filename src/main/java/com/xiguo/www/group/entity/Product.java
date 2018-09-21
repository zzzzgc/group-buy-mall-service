package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"productImages", "groupBuyProducts"})
@EqualsAndHashCode(callSuper = true, exclude = {"productImages", "groupBuyProducts"})
@Getter
@Setter

@Entity
@Table(appliesTo = "product", comment = "官方商品表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"})
public class Product extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "varchar(16) COMMENT '名称' ")
    private String name = "";
    @Column(nullable = false, columnDefinition = "varchar(16) COMMENT '规格' ")
    private String specification = "";
    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '描述' ")
    private String descriptor = "";
    @Column(nullable = false, columnDefinition = "decimal(8,2) COMMENT '商品价格' ")
    private BigDecimal price = new BigDecimal(0.00);
    @Column(nullable = false, columnDefinition = "bit(1) COMMENT '是否不限制数量 ' ")
    private Boolean limitQuantity = false;
    @Column(nullable = false, columnDefinition = "smallint(10) COMMENT '限制的数量(库存)' ")
    private Integer inventory = 0;
    @Column(nullable = false, columnDefinition = "smallint(10) COMMENT '销售总数(份)' ")
    private Integer sellTotalNumber = 0;
    @Column(nullable = false, columnDefinition = "bit(1) COMMENT '状态,0暂停 1开始 2删除' ")
    private Integer status = 0;

    /**
     * 团购商品
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<GroupBuyProduct> groupBuyProducts;

    /**
     * 商品图片,加载商品的时候附带加载图片
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductImage> productImages;

}
