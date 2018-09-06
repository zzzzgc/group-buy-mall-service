package com.xiguo.www.group.service.order;

import com.xiguo.www.group.entity.Order;
import com.xiguo.www.group.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Override
    @Transactional
    public Order save(Order order, Long userId, Long merchantUserId) {
        return orderRepository.save(order,userId,merchantUserId);
    }
}
