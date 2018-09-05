package com.xiguo.www.group.dto;

import com.xiguo.www.group.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 8:57
 */
@ToString(callSuper = true, exclude = {"user", "groupBuyNoutoasiakases", "orders", "groupBuyProducts"})
@EqualsAndHashCode(callSuper = true, exclude = {"user", "groupBuyNoutoasiakases", "orders", "groupBuyProducts"})
@Getter
@Setter
public class GroupBuyDto extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int status = 0;

    private String title = "";

    private String descriptor = "";

    private boolean canDistribution = false;

    private boolean canNoutoasiakas = false;

    private UserDto user;

    private Set<GroupBuyNoutoasiakasDto> groupBuyNoutoasiakases;

    private Set<GroupBuyProductDto> groupBuyProducts;

    private Set<OrderDto> orders = new HashSet<>();
}
