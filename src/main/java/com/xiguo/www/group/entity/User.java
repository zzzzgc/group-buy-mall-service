package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * 用户实体
 *
 * @author ZGC
 * @date Created in 下午 10:03 2018/8/24
 */
@ToString(callSuper = true, exclude = {"customerOrders", "groupBuys", "orders", "userShop", "userMerchantDefaultSettings", "customerDefaultSettings", "careAboutGroupBuys", "noutoasiakas"})
@EqualsAndHashCode(callSuper = true, exclude = {"customerOrders","groupBuys", "orders", "userShop", "userMerchantDefaultSettings", "customerDefaultSettings", "careAboutGroupBuys", "noutoasiakas"})
@Getter
@Setter

@Entity
@Table(appliesTo = "user", comment = "用户表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"})
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "varchar(16) COMMENT '微信昵称(刚进入平台时候的名称)' ")
    private String weChatName = "";
    @Column(unique = true, columnDefinition = "varchar(16) COMMENT '用户昵称' ")
    private String nickName = "";
    @JsonIgnore
    @Column(nullable = false, unique = true, columnDefinition = "varchar(28) COMMENT '微信用户唯一编号' ")
    private String openId = "";
    @Column(nullable = false, columnDefinition = "varchar(250) COMMENT '用户头像图片地址' ")
    private String imageUrl = "";
    @Column(nullable = false, columnDefinition = "varchar(30) COMMENT '城市' ")
    private String city = "";
    @Column(nullable = false, columnDefinition = "varchar(30) COMMENT '省市' ")
    private String province = "";
    @Column(nullable = false, columnDefinition = "varchar(20) COMMENT '国家' ")
    private String country = "";
    @Column(nullable = false, columnDefinition = "tinyint(1) COMMENT '性别 1男 2女' ")
    private int gender = 0;
    @Column(nullable = false, columnDefinition = "varchar(200) COMMENT '微信支付二维码URL' ")
    private String weChatPayQrCodeUrl = "";
    @Column(nullable = false, columnDefinition = "varchar(180) COMMENT '支付宝支付二维码URL' ")
    private String aliPayQrCodeUrl = "";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<GroupBuy> groupBuys;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchantUser")
    private Set<Order> customerOrders;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserShop userShop;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserMerchantDefaultSetting> userMerchantDefaultSettings;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserCustomerDefaultSetting> customerDefaultSettings;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserCareAboutGroupBuy> careAboutGroupBuys;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Noutoasiakas> noutoasiakas;

    public User(Long id) {
        this.id = id;
    }

    public User() {
    }
}

