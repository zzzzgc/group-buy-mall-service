package com.xiguo.www.group.repository.groupBuy;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "groupBuys", path = "groupBuys")
public interface GroupBuyRepository extends JpaRepository<GroupBuy, Long>, GroupBuyRepositoryInterface {

    /**
     * api/groupBuys/search/findByUserId?userId=xxx
     * 获取用户所有团购
     *
     * @param userId 用户id
     * @return 所有团购信息
     */
    @RestResource(path = "findByUserId", rel = "findByUserId")
    @Query(value = "select gbr from #{#entityName} gbr where gbr.user.id = :userId")
    List<GroupBuy> findByUserId(@Param("userId") Long userId);

    @RestResource(path = "findByStatusAndSelfId", rel = "findByStatusAndSelfId")
    @Query(value = "select gbr from GroupBuy gbr where gbr.status = :status and gbr.user.id = :userId")
    List<GroupBuy> findByStatusAndUser(@Param("userId") Long userId, @Param("status") int status);


    GroupBuy findByUserAndId(User user, Long groupBuyId);
}
