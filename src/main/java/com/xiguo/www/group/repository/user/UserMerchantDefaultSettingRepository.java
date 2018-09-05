package com.xiguo.www.group.repository.user;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.entity.UserMerchantDefaultSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "userMerchantDefaultSettings", path = "userMerchantDefaultSettings")
public interface UserMerchantDefaultSettingRepository extends JpaRepository<UserMerchantDefaultSetting, Long> {
    // api/UserMerchantDefaultSettingDto/search/findUserMerchantDefaultSettingsByUser?id=xxxx&nickName=xxx

    @RestResource(path = "findUserMerchantDefaultSettingsByUser", rel = "findUserMerchantDefaultSettingsByUser")
    List<UserMerchantDefaultSetting> findUserMerchantDefaultSettingsByUser(@Param("user") User user);
}
