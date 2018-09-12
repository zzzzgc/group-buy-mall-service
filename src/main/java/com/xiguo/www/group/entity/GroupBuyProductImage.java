package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"groupBuyProduct"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuyProduct"})
@Getter
@Setter

@Data
@Entity
@Table(appliesTo = "group_buy_product_image", comment = "团购商品图片表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updateAt"})
public class GroupBuyProductImage extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, columnDefinition = "varchar(250) COMMENT '图片地址' ")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private GroupBuyProduct groupBuyProduct;
}
