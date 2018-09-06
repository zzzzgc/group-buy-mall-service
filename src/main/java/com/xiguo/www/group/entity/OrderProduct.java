package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"}, allowGetters = true)
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
//    @JsonIgnore
    private GroupBuyProduct groupBuyProduct;

    @Transient
//    @Basic()
    private Long groupBuyProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

//    @JsonIgnore
//    public GroupBuyProduct getGroupBuyProduct() {
//        return groupBuyProduct;
//    }
//
//    public void setGroupBuyProduct(GroupBuyProduct groupBuyProduct) {
//        this.groupBuyProduct = groupBuyProduct;
//    }

//    public Long getGroupBuyProductId() {
//        return groupBuyProductId;
//    }
//
//    public void setGroupBuyProductId(Long groupBuyProductId) {
//        this.groupBuyProductId = groupBuyProductId;
//    }
}
