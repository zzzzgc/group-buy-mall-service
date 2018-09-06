package com.xiguo.www.group.repository.user;

import com.xiguo.www.group.entity.User;

import java.util.Set;

/**
 * @author: ZGC
 * @date Created in 2018/8/29 下午 4:56
 */
public interface UserRepositoryInterface {

    /**
     * 获取用户关注的所有团长和他们的团购信息
     *
     * @param userId
     * @return
     */
    Set<User> findAllCareAboutUserGroupBuyInfo(Long userId);
}
