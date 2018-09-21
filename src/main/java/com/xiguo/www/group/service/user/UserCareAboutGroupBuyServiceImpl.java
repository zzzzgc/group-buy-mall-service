package com.xiguo.www.group.service.user;

import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.entity.UserCareAboutGroupBuy;
import com.xiguo.www.group.repository.user.UserCareAboutGroupBuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/9/13 上午 10:50
 */
@Service("userCareAboutGroupBuyService")
public class UserCareAboutGroupBuyServiceImpl implements UserCareAboutGroupBuyService{

    @Autowired
    UserCareAboutGroupBuyRepository userCareAboutGroupBuyRepository;

    @Override
    public UserCareAboutGroupBuy findCareAboutUserByCareAboutUserId(Long userId, Long careAboutUserId) {
        return userCareAboutGroupBuyRepository.findByUser_IdAndOtherUser_Id(userId,careAboutUserId);
    }

    @Override
    public UserCareAboutGroupBuy saveCareAboutUser(Long userId, Long careAboutUserId) {
        UserCareAboutGroupBuy userCareAboutGroupBuy = new UserCareAboutGroupBuy();
        userCareAboutGroupBuy.setOtherUser(new User(careAboutUserId));
        userCareAboutGroupBuy.setUser(new User(userId));
        return userCareAboutGroupBuyRepository.save(userCareAboutGroupBuy);
    }
}
