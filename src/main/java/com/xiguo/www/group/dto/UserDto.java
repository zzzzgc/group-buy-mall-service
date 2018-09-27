package com.xiguo.www.group.dto;

import com.xiguo.www.group.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 8:57
 */
@ToString(callSuper = true, exclude = {"groupBuys", "orders", "userShop", "userMerchantDefaultSettings", "customerDefaultSettings", "careAboutGroupBuys", "noutoasiakas"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuys", "orders", "userShop", "userMerchantDefaultSettings", "customerDefaultSettings", "careAboutGroupBuys", "noutoasiakas"})
@Getter
@Setter
public class UserDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private Date createAt;

    private Date updatedAt;

    private String weChatName = "";

    private String nickName = "";
// 不输出
//    private String openId = "";

    private String imageUrl = "";

    private String city = "";

    private String province = "";

    private String country = "";

    private int gender = 0;

    private Set<GroupBuyDto> groupBuys;

    private Set<OrderDto> orders;

//    private UserShopDto userShop;

    private Set<UserMerchantDefaultSettingDto> userMerchantDefaultSettings;

    private Set<UserCustomerDefaultSettingDto> customerDefaultSettings;

    private Set<UserCareAboutGroupBuyDto> careAboutGroupBuys;

    private Set<NoutoasiakasDto> noutoasiakas;

}

