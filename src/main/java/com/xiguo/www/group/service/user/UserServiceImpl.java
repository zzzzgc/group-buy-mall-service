package com.xiguo.www.group.service.user;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Set;

/**
 * @author: ZGC
 * @date Created in 2018/9/4 下午 6:26
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional()
    public Set<User> findAllCareAboutUserGroupBuyInfo(Long userId) {
        Set<User> allCareAboutUserGroupBuyInfo = userRepository.findAllCareAboutUserGroupBuyInfo(userId);
        return allCareAboutUserGroupBuyInfo;
    }

    @Override
    public GroupBuy findByUserId(Long userId) {
        return null;
    }
}
