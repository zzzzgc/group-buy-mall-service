package com.xiguo.www.group.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.io.Serializable;

@ToString(callSuper = true, exclude = {"user"})
@EqualsAndHashCode(callSuper = true, exclude = {"user"})
@Getter
@Setter

@Entity
@Table(appliesTo = "user_authentication", comment = "用户认证表")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler", "createAt", "updatedAt"})
public class UserAuthentication extends BaseEntity implements Serializable {

    @Column(nullable = false, columnDefinition = "varchar(14) COMMENT '手机号码,国外手机可能包含区号' ")
    private String phone = "";
    @Column(nullable = false, columnDefinition = "varchar(11) COMMENT '团长权限, 0无权限 1有权限' ")
    private int openGroupPermissions = 0;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
