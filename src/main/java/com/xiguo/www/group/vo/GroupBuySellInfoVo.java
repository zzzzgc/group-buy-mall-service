package com.xiguo.www.group.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/9/18 下午 12:35
 */
@Data
public class GroupBuySellInfoVo implements Serializable {
    /**
     * 团购id
     */
    private Long id;
    /**
     * 团购名称
     */
    private String title;
    /**
     * 团购创建时间
     */
    private Date createAt;
    /**
     * 商品
     */
    private List<ProductVo> products;
    /**
     * 团购总销售商品数量
     */
    private String totalSellNumber;
    /**
     * 团购参与者数量
     */
    private String totalParticipantNumber;
    /**
     * 团购平均消费价格
     */
    private BigDecimal averageConsumePrice;
    /**
     * 团购总销售价格
     */
    private BigDecimal totalSellPrice;
}
