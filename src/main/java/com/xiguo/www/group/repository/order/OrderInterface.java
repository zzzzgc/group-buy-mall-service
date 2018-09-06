package com.xiguo.www.group.repository.order;

import com.xiguo.www.group.entity.Order;

/**
 * @author: ZGC
 * @date Created in 2018/9/5 下午 4:19
 */
public interface OrderInterface {
    /**
     * 保存订单
     * @param order 订单
     * @param merchantUserId
     * @return 订单
     */
    public Order save(Order order, Long userId, Long merchantUserId);
}
