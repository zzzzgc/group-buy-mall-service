package com.xiguo.www.group.repository.order;

import com.xiguo.www.group.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * @author ZGC
 * @date Created in 下午 9:00 2018/8/24
 */
@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends JpaRepository<Order, Long>, OrderInterface {
    /**
     * 根据用户id获取订单
     * 后期扩展分页给出信息
     * @param userId 客户用户id
     * @return 订单集合
     */
    @Query("select o from group_buy_order o where o.user.id = :userId order by o.id desc")
    List<Order> findByCustomerUserId(@Param("userId") Long userId);

    /**
     * 根据用户id获取订单
     * 后期扩展分页给出信息
     * @param userId 客户用户id
     * @return 订单集合
     */
    @Query("select o from group_buy_order o join fetch o.orderProducts where o.merchantUser.id = :userId group by o.id order by o.isDelivery asc,o.id asc")
    List<Order> findByMerchantUserId(@Param("userId") Long userId);
}
