package com.xiguo.www.group.service.user;

import com.xiguo.www.group.entity.User;

import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
public interface UserService {

    /**
     * 获取用户关注的所有团长和他们的团购信息
     *
     * @param userId 用户id
     * @return
     */
    List<User> findAllCareAboutUserGroupBuyInfo(Long userId);
}
