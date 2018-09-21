package com.xiguo.www.group.service.order;

import com.xiguo.www.group.entity.Order;

import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/9/5 下午 4:30
 */
public interface OrderService {
    Order save(Order order, Long groupBuyId, Long userId, Long merchantUserId);

    /**
     * 根据用户id获取订单
     * 后期扩展分页给出信息
     * @param userId 客户用户id
     * @return 订单集合
     */
    List<Order> findByCustomerUserId(Long userId);

    /**
     * 获取订单详细 根据订单id
     * @param orderId 订单id
     * @return 订单详细
     */
    Order findDetailById(Long orderId);

    /**
     * 根据商家用户id获取订单
     * 后期扩展分页给出信息
     * @param userId 商家用户id
     * @param searchType
     * @param searchText
     * @return 订单集合
     */
    List<Order> findByMerchantUserId(Long userId, int searchType, String searchText);


}
