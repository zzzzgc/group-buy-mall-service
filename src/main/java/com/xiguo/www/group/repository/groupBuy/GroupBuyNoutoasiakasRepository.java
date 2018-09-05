package com.xiguo.www.group.repository.groupBuy;

import com.xiguo.www.group.entity.GroupBuyNoutoasiakas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * 团购自提点
 *
 * 只可以创建,不许更改,不可删除(除非 数据太多,清理历史)
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "groupBuyNoutoasiakases", path = "groupBuyNoutoasiakases")
public interface GroupBuyNoutoasiakasRepository extends JpaRepository<GroupBuyNoutoasiakas, Long> {
    /**
     * api/groupBuyNoutoasiakaVos/search/findByGroupBuyId?groupBuyId=xxx
     * @param groupBuyId
     * @return
     */
    @RestResource(path = "findByGroupBuyId", rel = "findByGroupBuyId")
    @Query(value="select gbn from #{#entityName} as gbn where gbn.groupBuy.id = :groupBuyId")
    public List<GroupBuyNoutoasiakas> findByGroupBuyId (@Param("groupBuyId")Long groupBuyId);
}
