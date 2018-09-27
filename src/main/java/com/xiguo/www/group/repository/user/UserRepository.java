package com.xiguo.www.group.repository.user;

import com.xiguo.www.group.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryInterface {
    /**
     * 根据昵称查找用户
     *
     * @param nickName 昵称
     * @return 用户
     */
    @RestResource(path = "name", rel = "name")
    User findByNickName(@Param("name") String nickName);

    /**
     * 根据openId查找用户
     *
     * @param openId 微信openId
     * @return 用户
     */
    User findByOpenId(String openId);

    @RestResource(exported = false)
    @Override
    void delete(User entity);

    /**
     * api/users/search/findByGroupBuyId?groupBuyId = xxx
     *
     * @param groupBuyId
     * @return
     */
    @RestResource(path = "findByGroupBuyId", rel = "findByGroupBuyId")
    @Query("select u from GroupBuy gb  join User u on gb.user.id = u.id where gb.id = :groupBuyId")
    User findByGroupBuyId(@Param("groupBuyId") Long groupBuyId);
}
