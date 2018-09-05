package com.xiguo.www.group.repository.user;
// 若将XXXRepositoryImpl与XXXRepository接口放在同意包下，XXXRepositoryImpl不需要添加@Repository注解，但是当XXXRepositoryImpl与XXXRepository接口不在同一包下，需要在在XXXRepositoryImpl类上加@Repository注解进行修饰

import com.xiguo.www.group.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/8/29 下午 4:50
 */
public class UserRepositoryImpl implements UserRepositoryInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllCareAboutUserGroupBuyInfo(Long userId) {
        User user = userRepository.getOne(userId);

        List<User> Users = new ArrayList<>();
        user.getCareAboutGroupBuys().forEach(
                careAboutGroupBuy -> {
                    User otherUser = careAboutGroupBuy.getOtherUser();
                    Users.add(otherUser);
                    otherUser.getGroupBuys().forEach(
                            groupBuy -> {
                                groupBuy.setUser(null);
                                groupBuy.getGroupBuyProducts().forEach(
                                        groupBuyProduct -> groupBuyProduct.getGroupBuyProductImages().size()
                                );
                            }
                    );
                }
        );
        return Users;
    }
}
