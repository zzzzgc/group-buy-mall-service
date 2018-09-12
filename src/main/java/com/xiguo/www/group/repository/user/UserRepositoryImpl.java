package com.xiguo.www.group.repository.user;
// 若将XXXRepositoryImpl与XXXRepository接口放在同意包下，XXXRepositoryImpl不需要添加@Repository注解，但是当XXXRepositoryImpl与XXXRepository接口不在同一包下，需要在在XXXRepositoryImpl类上加@Repository注解进行修饰

import com.xiguo.www.group.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author: ZGC
 * @date Created in 2018/8/29 下午 4:50
 */
public class UserRepositoryImpl implements UserRepositoryInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public Set<User> findAllCareAboutUserGroupBuyInfo(Long userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (!byId.isPresent())  {
            // 抛异常?
        }
        User user = byId.get();
        HashSet<User> mUsers = new HashSet<>();
        for (UserCareAboutGroupBuy userCareAboutGroupBuy : user.getCareAboutGroupBuys()) {
            User otherUser = userCareAboutGroupBuy.getOtherUser();
            boolean add = mUsers.add(otherUser);
            for (GroupBuy groupBuy : otherUser.getGroupBuys()) {
                for (GroupBuyProduct groupBuyProduct : groupBuy.getGroupBuyProducts()) {
                    groupBuyProduct.getGroupBuyProductImages().size();
                }
            }
        }
        return mUsers;
    }
}
