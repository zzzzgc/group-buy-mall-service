package com.xiguo.www.group.dto;

import com.xiguo.www.group.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 下午 8:57
 */
@ToString(callSuper = true, exclude = {"user"})
@EqualsAndHashCode(callSuper = true, exclude = {"user"})
@Getter
@Setter
public class UserShopDto extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Date createAt;

    private Date updatedAt;
    
    private String shopName = "";
    
    private Integer phone = 0;
    
    private String address = "";
    
    private UserDto user;

}
