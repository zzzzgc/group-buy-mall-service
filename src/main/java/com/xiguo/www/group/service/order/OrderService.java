package com.xiguo.www.group.service.order;

import com.xiguo.www.group.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
@Service("orderService")
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
}
