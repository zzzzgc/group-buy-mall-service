package com.xiguo.www.group.service.order;

import com.xiguo.www.group.entity.Order;
import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    @Transactional
    public Order save(Order order, Long groupBuyId, Long userId, Long merchantUserId) {
        return orderRepository.save(order, groupBuyId, userId, merchantUserId);
    }

    @Override
    public List<Order> findByCustomerUserId(Long userId) {
        List<Order> orders = orderRepository.findByCustomerUserId(userId);
        return orders;
    }

    @Override
    public Order findDetailById(Long orderId) {
        return orderRepository.findDetailById(orderId);
    }

    @Override
    public List<Order> findByMerchantUserId(Long userId) {
        List<Order> orders = orderRepository.findByMerchantUserId(userId);
        return orders;
    }
}
