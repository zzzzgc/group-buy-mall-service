package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"order", "groupBuyProduct"})
@EqualsAndHashCode(callSuper = true, exclude = {"order", "groupBuyProduct"})
@Getter
@Setter

@Entity(name = "group_buy_order_product")
@Table(appliesTo = "group_buy_order_product", comment = "订单商品表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"})
public class OrderProduct extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "varchar(16) COMMENT '商品名称' ")
    private String name = "";
    @Column(nullable = false, columnDefinition = "varchar(250) COMMENT '商品图片地址' ")
    private String imageUrl = "";
    @Column(nullable = false, columnDefinition = "decimal(8,2) COMMENT '商品价格' ")
    private BigDecimal price = new BigDecimal(0.00);
    @Column(nullable = false, columnDefinition = "smallint(8) COMMENT '购买数量' ")
    private int number = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupBuyProduct groupBuyProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

}
