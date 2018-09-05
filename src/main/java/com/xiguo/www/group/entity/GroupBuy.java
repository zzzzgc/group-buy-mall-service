package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 团购
 *
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"user", "groupBuyNoutoasiakases", "orders", "groupBuyProducts"})
@EqualsAndHashCode(callSuper = true, exclude = {"user", "groupBuyNoutoasiakases", "orders", "groupBuyProducts"})
@Getter
@Setter

@Entity
@Table(appliesTo = "group_buy", comment = "团购表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class GroupBuy extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false, columnDefinition = "tinyint(1) COMMENT '团购状态(待定)' ")
    private int status = 0;
    @NotBlank(message = "标题不可以为空")
    @Column(nullable = false, columnDefinition = "varchar(26) COMMENT '标题' ")
    private String title = "";
    @Column(nullable = false, columnDefinition = "varchar(150) COMMENT '描述' ")
    private String descriptor = "";
    @Column(nullable = false, columnDefinition = "bit(1) COMMENT '是否可以配送' ")
    private boolean canDistribution = false;
    @Column(nullable = false, columnDefinition = "bit(1) COMMENT '是否可以自提' ")
    private boolean canNoutoasiakas = false;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JsonIgnore
    private User user=new User();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "groupBuy")
    private Set<GroupBuyNoutoasiakas> groupBuyNoutoasiakases = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "groupBuy")
    private Set<GroupBuyProduct> groupBuyProducts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "groupBuy")
    private Set<Order> orders = new HashSet<>();

    public GroupBuy(Long id) {
        this.id = id;
    }

    public GroupBuy() {
    }
}
