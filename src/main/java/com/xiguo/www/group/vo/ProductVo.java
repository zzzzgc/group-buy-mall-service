package com.xiguo.www.group.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: ZGC
 * @date Created in 2018/9/18 下午 12:37
 */
@Data
public class ProductVo {
    private String name;
    private BigDecimal price;
    private Integer sellTotalNumber;
}
