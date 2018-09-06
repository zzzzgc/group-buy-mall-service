package com.xiguo.www.group.dto;

import com.xiguo.www.group.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 8:57
 */
@ToString(callSuper = true, exclude = {"groupBuyNoutoasiakases", "user"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuyNoutoasiakases", "user"})
@Getter
@Setter
public class NoutoasiakasDto extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String address = "";

    private String province = "";

    private String city = "";

    private String district = "";

    private String detailAddress = "";

    private String nickName = "";

    private List<GroupBuyNoutoasiakasDto> groupBuyNoutoasiakases;

    // private UserDto user;

}
