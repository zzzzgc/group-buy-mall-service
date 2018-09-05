package com.xiguo.www.group.dto;

import com.xiguo.www.group.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 8:55
 */
@ToString(callSuper = true, exclude = {"groupBuyProduct"})
@EqualsAndHashCode(callSuper = true, exclude = {"groupBuyProduct"})
@Getter
@Setter
public class GroupBuyProductImageDto extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String url;

    private GroupBuyProductDto groupBuyProduct;

}
