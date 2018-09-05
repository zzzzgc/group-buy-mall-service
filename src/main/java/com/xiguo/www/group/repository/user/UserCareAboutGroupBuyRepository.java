package com.xiguo.www.group.repository.user;

import com.xiguo.www.group.entity.UserCareAboutGroupBuy;
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
@RepositoryRestResource(collectionResourceRel = "userCareAboutGroupBuys", path = "userCareAboutGroupBuys")
public interface UserCareAboutGroupBuyRepository extends JpaRepository<UserCareAboutGroupBuy, Long> {

    /**
     * api/userCareAboutGroupBuys/search/findByUserId?userId=xxx
     * @param userId
     * @return
     */
    @RestResource(path = "findByUserId", rel = "findByUserId")
    @Query("select uca from UserCareAboutGroupBuy uca where uca.user.id = :userId")
    List<UserCareAboutGroupBuy> findByUserId(@Param("userId")Long userId);
}
