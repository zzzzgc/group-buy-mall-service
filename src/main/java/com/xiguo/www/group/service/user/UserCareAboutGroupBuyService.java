package com.xiguo.www.group.service.user;

import com.xiguo.www.group.entity.UserCareAboutGroupBuy;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
public interface UserCareAboutGroupBuyService {

    /**
     * 获取指定的关注用户,不存在会返回空
     * @param userId 用户id
     * @param careAboutUserId 关注的用户(团长)id
     * @return 关注的用户(团长)
     */
    UserCareAboutGroupBuy findCareAboutUserByCareAboutUserId(Long userId, Long careAboutUserId);

    /**
     * 保存指定的关注用户,不存在会返回空
     * @param userId 用户id
     * @param careAboutUserId 关注的用户(团长)id
     * @return 关注的用户(团长)
     */
    UserCareAboutGroupBuy saveCareAboutUser(Long userId, Long careAboutUserId);
}
