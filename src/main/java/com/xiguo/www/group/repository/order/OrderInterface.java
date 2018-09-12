package com.xiguo.www.group.repository.order;

import com.xiguo.www.group.entity.Order;

/**
 * @author: ZGC
 * @date Created in 2018/9/5 下午 4:19
 */
public interface OrderInterface {
    /**
     * 保存订单
     *
     * @param order          订单
     * @param groupBuyId
     * @param merchantUserId
     * @return 订单
     */
    Order save(Order order, Long groupBuyId, Long userId, Long merchantUserId);

    /**
     * 根据订单id获取订单详细
     * order -> groupBuy -> noutoasiakas
     * order -> orderProduct
     *
     * @param orderId 订单id
     * @return 订单详细
     */
    Order findDetailById(Long orderId);
}
