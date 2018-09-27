package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
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
@Table(appliesTo = "user_shop", comment = "表注释")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"})
public class UserShop extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "varchar(12) COMMENT '店铺名称' ")
    private String shopName = "";
    @Column(nullable = false, columnDefinition = "int(11) COMMENT '联系电话' ")
    private Integer phone = 0;
    @Column(nullable = false, columnDefinition = "varchar(25) COMMENT '店铺地址' ")
    private String address = "";

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}
