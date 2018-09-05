package com.xiguo.www.group.dto;

import com.xiguo.www.group.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 8:57
 */
@ToString(callSuper = true, exclude = {"user"})
@EqualsAndHashCode(callSuper = true, exclude = {"user"})
@Getter
@Setter
public class UserMerchantDefaultSettingDto extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date createAt;

    private Date updatedAt;
    
    private Boolean canDistribution = true;
    
    private Boolean canNoutoasiakas = true;
    
    private UserDto user;

}
