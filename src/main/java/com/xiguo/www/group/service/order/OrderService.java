package com.xiguo.www.group.service.order;

import com.xiguo.www.group.entity.Order;

/**
 * @author: ZGC
 * @date Created in 2018/9/5 下午 4:30
 */
public interface OrderService {
    Order save(Order order, Long userId, Long merchantUserId);
}
