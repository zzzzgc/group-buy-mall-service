package com.xiguo.www.group.repository.product;

import com.xiguo.www.group.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "orderProducts", path = "orderProducts")
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> , OrderProductInterface {
}
