package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"groupBuy", "groupBuyProductImages", "orderProducts"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuy", "groupBuyProductImages", "orderProducts"})
@Getter
@Setter

@Entity
@Table(appliesTo = "group_buy_product", comment = "团购商品表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"}, allowGetters = true)
public class GroupBuyProduct extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "varchar(16) COMMENT '名称' ")
    private String name = "";
    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '描述' ")
    private String descriptor = "";
    @Column(nullable = false, columnDefinition = "decimal(8,2) COMMENT '商品价格' ")
    private BigDecimal price = new BigDecimal(0.00);
    @Column(nullable = false, columnDefinition = "bit(1) COMMENT '是否不限制刷领 ' ")
    private Boolean limitQuantity = false;
    @Column(nullable = false, columnDefinition = "smallint(10) COMMENT '限制的数量(库存)' ")
    private Integer quantity = 0;
    @Column(nullable = false, columnDefinition = "smallint(10) COMMENT '销售总数' ")
    private Integer sellTotalNumber = 0;

    /**
     * 商品必须维护到团购
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private GroupBuy groupBuy;

    /**
     * 商品图片,加载商品的时候附带加载图片
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "groupBuyProduct")
    private Set<GroupBuyProductImage> groupBuyProductImages;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "groupBuyProduct")
    @JsonIgnore
    private Set<OrderProduct> orderProducts;
}
