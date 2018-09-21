package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ZGC
 * @date Created in 下午 5:31 2018/8/25
 */
@ToString(callSuper = true, exclude = {"otherUser", "user"})
@EqualsAndHashCode(callSuper = true, exclude = {"otherUser", "user"})
@Getter
@Setter

@Data
@Entity
//@Table
@Table(indexes = {@Index(name = "ux_user_care_about_user_other_user", columnList = "user_id,care_about_user_id", unique = true)})
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"})
public class UserCareAboutGroupBuy extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "care_about_user_id")
    private User otherUser;
}
