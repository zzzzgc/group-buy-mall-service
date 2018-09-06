package com.xiguo.www.group.dto;

import com.xiguo.www.group.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 8:57
 */
@ToString(callSuper = true, exclude = {"groupBuy", "user","orderProducts"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuy", "user","orderProducts"})
@Getter
@Setter
public class OrderDto extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Date createAt;

    private Date updatedAt;

    private String status = "";

    private Boolean isDelivery = false;

    private int logisticsType = 0;

    private String address = "";

    private String noutoasiakasId = "";

    private String noutoasiakasName = "";

    private String noutoasiakasAddress = "";

    private String userName = "";

    private String userHeadImage = "";

    private String phone = "";

    private String groupBuyName = "";

    private BigDecimal totalPrice = new BigDecimal(0.00);

    private BigDecimal payPrice = new BigDecimal(0.00);

    private String customerRemark = "";

    private String merchantRemark = "";

    // private GroupBuyDto groupBuy;

    // private UserDto user;

    private List<OrderProductDto> orderProducts;


}
