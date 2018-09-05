package com.xiguo.www.group.repository.groupBuy;

import com.xiguo.www.group.entity.GroupBuyProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "groupBuyProductImages", path = "groupBuyProductImages")
public interface GroupBuyProductImageRepository extends JpaRepository<GroupBuyProductImage, Long> {
}
