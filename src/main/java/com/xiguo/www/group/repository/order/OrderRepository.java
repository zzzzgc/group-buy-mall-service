package com.xiguo.www.group.repository.order;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends JpaRepository<Order, Long>, OrderInterface {
}
