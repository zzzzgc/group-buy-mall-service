package com.xiguo.www.group.repository.product;

import com.xiguo.www.group.entity.GroupBuyProduct;
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
@RepositoryRestResource(collectionResourceRel = "groupBuyProducts", path = "groupBuyProducts")
public interface GroupBuyProductRepository extends JpaRepository<GroupBuyProduct, Long> {
    // api/groupBuyProductDtos/search/findGroupBuyProductsByGroupBuy?id=xxxx&nickName=xxx

    /**
     * api/groupBuyProduct/search/findByGroupBuyId?groupBuyId=xxx
     * 根据团购Id查找商品
     * @param groupBuyId 团购id
     * @return 团购商品
     */
    @RestResource(path = "findByGroupBuyId", rel = "findByGroupBuyId")
    @Query(value="select gbp from #{#entityName} gbp where gbp.groupBuy.id = :groupBuyId")
    List<GroupBuyProduct> findByGroupBuyId(@Param("groupBuyId") Long groupBuyId);
}
