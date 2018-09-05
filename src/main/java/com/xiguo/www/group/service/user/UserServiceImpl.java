package com.xiguo.www.group.service.user;

import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/9/4 下午 6:26
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllCareAboutUserGroupBuyInfo(Long userId) {
        return userRepository.findAllCareAboutUserGroupBuyInfo(userId);
    }
}
