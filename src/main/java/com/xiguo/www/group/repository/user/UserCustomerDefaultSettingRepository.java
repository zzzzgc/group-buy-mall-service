package com.xiguo.www.group.repository.user;

import com.xiguo.www.group.entity.UserCustomerDefaultSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "userCustomerDefaultSettings", path = "userCustomerDefaultSettings")
public interface UserCustomerDefaultSettingRepository extends JpaRepository<UserCustomerDefaultSetting, Long> {
}
