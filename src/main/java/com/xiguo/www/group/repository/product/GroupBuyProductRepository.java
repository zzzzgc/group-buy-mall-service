package com.xiguo.www.group.repository.product;

import com.xiguo.www.group.entity.GroupBuyProduct;
import com.xiguo.www.group.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "groupBuyProducts", path = "groupBuyProducts")
public interface GroupBuyProductRepository extends JpaRepository<GroupBuyProduct, Long>, GroupBuyProductInterface {
    // api/groupBuyProductDtos/search/findGroupBuyProductsByGroupBuy?id=xxxx&nickName=xxx

    /**
     * api/groupBuyProduct/search/findByGroupBuyId?groupBuyId=xxx
     * 根据团购Id查找商品
     *
     * @param groupBuyId 团购id
     * @return 团购商品
     */
    @RestResource(path = "findByGroupBuyId", rel = "findByGroupBuyId")
    @Query(value = "select gbp from #{#entityName} gbp where gbp.groupBuy.id = :groupBuyId")
    List<GroupBuyProduct> findByGroupBuyId(@Param("groupBuyId") Long groupBuyId);

    /**
     * 更新库存
     * @param productId 商品id
     * @param number 变更数量
     * @return 成功与否
     */

//    @Transactional(rollbackFor = Exception.class)

    /**
     * SERVICE_SYSTEM_STATEMENT  资源:商品消耗量  操作:订正  唯一:是  重要:是 原因:影响库存
     * <p>
     * 唯一的商品销售数量订正
     * <p>
     * 变更数量
     * 正数为增加数量,比如 +1  2
     * 复数为减少数量,比如 -1 -2
     * 不变的情况为, 0 -0 +0
     *
     * @param productId 商品id
     * @param number    商品增加的数量
     * @return 0没更新  1更新成功 >1异常
     */
    @Modifying
    @Query(value = "update GroupBuyProduct p set p.sellTotalNumber = p.sellTotalNumber + :number where (case p.limitQuantity when 1 then 1 else (p.inventory - (p.sellTotalNumber + :number)) end) >= 0 and p.id = :productId")
    int updateInventory(@Param("productId") Long productId, @Param("number") int number);
}
