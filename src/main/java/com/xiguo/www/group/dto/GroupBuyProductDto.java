package com.xiguo.www.group.dto;

import com.xiguo.www.group.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 8:56
 */
@ToString(callSuper = true, exclude = {"groupBuy", "groupBuyProductImages", "orderProducts"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuy", "groupBuyProductImages", "orderProducts"})
@Getter
@Setter
public class GroupBuyProductDto extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name = "";
    
    private String descriptor = "";
    
    private BigDecimal price = new BigDecimal(0.00);
    
    private Boolean limitQuantity = false;
    
    private Integer quantity = 0;
    
    private Integer sellTotalNumber = 0;
    
    // private GroupBuyDto groupBuy;
    
    private Set<GroupBuyProductImageDto> groupBuyProductImages=new HashSet<>();
    
    private Set<OrderProductDto> orderProducts =new HashSet<>();
}
