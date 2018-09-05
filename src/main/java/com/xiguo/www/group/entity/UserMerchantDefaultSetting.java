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
@Table(appliesTo = "user_merchant_default_setting", comment = "用户团长默认配置表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"}, allowGetters = true)
public class UserMerchantDefaultSetting extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "bit(1) COMMENT '是否配送' ")
    private Boolean canDistribution = true;
    @Column(nullable = false, columnDefinition = "bit(1) COMMENT '是否自提' ")
    private Boolean canNoutoasiakas = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
