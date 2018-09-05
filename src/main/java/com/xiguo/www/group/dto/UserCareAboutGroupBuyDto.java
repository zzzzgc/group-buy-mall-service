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
@ToString(callSuper = true, exclude = {"user", "otherUser"})
@EqualsAndHashCode(callSuper = true, exclude = {"user", "otherUser"})
@Getter
@Setter
public class UserCareAboutGroupBuyDto extends BaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Date createAt;

    private Date updatedAt;

    private UserDto user;

    private UserDto otherUser;

}
