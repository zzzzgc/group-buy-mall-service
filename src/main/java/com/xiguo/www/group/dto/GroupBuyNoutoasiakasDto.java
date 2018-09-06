package com.xiguo.www.group.dto;

import com.xiguo.www.group.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 8:46
 */
@ToString(callSuper = true, exclude = {"groupBuy", "noutoasiakas"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuy", "noutoasiakas"})
@Getter
@Setter
public class GroupBuyNoutoasiakasDto extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String address = "";

    private String nickName = "";

    // private GroupBuyDto groupBuy;

    // private NoutoasiakasDto noutoasiakas;
}
