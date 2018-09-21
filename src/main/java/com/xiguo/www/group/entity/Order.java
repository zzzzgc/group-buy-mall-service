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
import java.util.List;
import java.util.Objects;

/**
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"groupBuy", "user", "orderProducts", "merchantUser"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuy", "user", "orderProducts", "merchantUser"})
@Getter
@Setter

@Entity(name = "group_buy_order")
@Table(appliesTo = "group_buy_order", comment = "订单表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler", "updatedAt"})
public class Order extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "tinyint(1) COMMENT '状态' ")
    private int status = 0;
    @Column(nullable = false, columnDefinition = "bit(1) COMMENT '是否发货' ")
    private Boolean isDelivery = false;
    @Column(nullable = false, columnDefinition = "tinyint(1) COMMENT '物流类型 0未知 1快递 2自提' ")
    private int logisticsType = 0;
    @Column(nullable = false, columnDefinition = "varchar(70) COMMENT '用户自填收货地址' ")
    private String address = "";
    @Column(nullable = false, columnDefinition = "varchar(10) COMMENT '用户指定自提点Id' ")
    private String noutoasiakasId = "";
    @Column(nullable = false, columnDefinition = "varchar(8) COMMENT '用户指定自提点名称' ")
    private String noutoasiakasName = "";
    @Column(nullable = false, columnDefinition = "varchar(70) COMMENT '用户指定自提点地址' ")
    private String noutoasiakasAddress = "";
    @Column(nullable = false, columnDefinition = "varchar(15) COMMENT '用户自填收件人' ")
    private String userName = "";
    @Column(nullable = false, columnDefinition = "varchar(250) COMMENT '用户自填头像' ")
    private String userHeadImage = "";
    @Column(nullable = false, columnDefinition = "varchar(15) COMMENT '用户自填手机号码' ")
    private String phone = "";
    @Column(nullable = false, columnDefinition = "varchar(16) COMMENT '团购名称' ")
    private String groupBuyName = "";
    @Column(nullable = false, columnDefinition = "decimal(10,2) COMMENT '总金额' ")
    private BigDecimal totalPrice = new BigDecimal(0.00);
    @Column(nullable = false, columnDefinition = "decimal(8,2) COMMENT '支付金额' ")
    private BigDecimal payPrice = new BigDecimal(0.00);
    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '客户备注' ")
    private String customerRemark = "";
    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '商家备注' ")
    private String merchantRemark = "";

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
    private GroupBuy groupBuy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
    private User merchantUser;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderProduct> orderProducts;

    public GroupBuy getGroupBuy() {
        return groupBuy;
    }

    @JsonIgnore
    public void setGroupBuy(GroupBuy groupBuy) {
        this.groupBuy = groupBuy;
    }
}
