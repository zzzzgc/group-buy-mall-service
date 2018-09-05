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

/**
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"user"})
@EqualsAndHashCode(callSuper = true, exclude = {"user"})
@Getter
@Setter

@Entity
@Table(appliesTo = "user_customer_default_setting", comment = "用户团员默认设置")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"}, allowGetters = true)
public class UserCustomerDefaultSetting extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "varchar(15) COMMENT '默认收件人' ")
    private String name = "";
    @Column(nullable = false, columnDefinition = "int(11) COMMENT '默认手机' ")
    private Integer phone = 0;
    @Column(nullable = false, columnDefinition = "varchar(70) COMMENT '默认地址' ")
    private String address = "";

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
}
