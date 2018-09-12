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
import java.util.List;
import java.util.Objects;

/**
 * 用户的自提点 模板 模板 模板
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"user","groupBuyNoutoasiakases"})
@EqualsAndHashCode(callSuper = true, exclude = {"user","groupBuyNoutoasiakases"})
@Getter
@Setter

@Entity
@Table(appliesTo = "noutoasiakas", comment = "用户自提点表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"})
public class Noutoasiakas extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "varchar(70) COMMENT '地址' ")
    private String address = "";
    @Column(nullable = false, columnDefinition = "varchar(10) COMMENT '省' ")
    private String province = "";
    @Column(nullable = false, columnDefinition = "varchar(10) COMMENT '市' ")
    private String city = "";
    @Column(nullable = false, columnDefinition = "varchar(10) COMMENT '区' ")
    private String district = "";
    @Column(nullable = false, columnDefinition = "varchar(40) COMMENT '详细地址' ")
    private String detailAddress = "";
    @Column(nullable = false, columnDefinition = "varchar(8) COMMENT '自提点昵称' ")
    private String nickName = "";

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "noutoasiakas")
    private List<GroupBuyNoutoasiakas> groupBuyNoutoasiakases;
    /**
     * 绑定的user_id 但是这个自提点不一定只能他用.这里的userId只能是绑定创建人
     */
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
}
