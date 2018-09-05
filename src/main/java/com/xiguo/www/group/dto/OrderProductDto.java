package com.xiguo.www.group.dto;

import com.xiguo.www.group.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 8:56
 */
@ToString(callSuper = true, exclude = {"groupBuyProduct", "order"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuyProduct", "order"})
@Getter
@Setter
public class OrderProductDto extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Date createAt;

    private Date updatedAt;
    
    private String name = "";
    
    private String imageUrl = "";
    
    private BigDecimal price = new BigDecimal(0.00);
    
    private int number = 0;
    
    private GroupBuyProductDto groupBuyProduct;

    private OrderDto order;

}
