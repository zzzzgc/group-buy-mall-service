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
import java.util.Date;
import java.util.Objects;

/**
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"groupBuy", "noutoasiakas"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuy", "noutoasiakas"})
@Getter
@Setter

@Entity
@Table(appliesTo = "group_buy_noutoasiakas", comment = "团购自提点表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"}, allowGetters = true)
public class GroupBuyNoutoasiakas extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '自提点地址' ")
    private String address = "";
    @Column(nullable = false, columnDefinition = "varchar(50) COMMENT '自提点昵称' ")
    private String nickName = "";

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonIgnore
    private GroupBuy groupBuy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Noutoasiakas noutoasiakas;

}
