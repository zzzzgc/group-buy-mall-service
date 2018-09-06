package com.xiguo.www.group.repository.order;

import com.xiguo.www.group.entity.*;
import com.xiguo.www.group.repository.product.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/9/5 下午 4:20
 */
public class OrderRepositoryImpl implements OrderInterface {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    @Modifying
    public Order save(Order order, Long userId, Long merchantUserId) {
        // 关联团员
        order.setUser(new User(userId));
        // 关联团长
        order.setMerchantUser(new User(merchantUserId));

        GroupBuy groupBuy = order.getGroupBuy();

        HashSet<Order> orders = new HashSet<>();
        orders.add(order);

        // 关联团购
        groupBuy.setOrders(orders);

        List<OrderProduct> orderProducts = order.getOrderProducts();
        if (orderProducts != null) {

            orderProducts.forEach(orderProduct -> {
                orderProduct.setOrder(order);
                HashSet<OrderProduct> orderProductsSet = new HashSet<>();
                orderProductsSet.add(orderProduct);
                orderProduct.getGroupBuyProduct().setOrderProducts(orderProductsSet);
            });
        }
        if (order.getId() != null) {
            List<OrderProduct> oldOrderProducts = orderRepository.getOne(order.getId()).getOrderProducts();
            orderProductRepository.deleteAll(oldOrderProducts);
        }
        order.setOrderProducts(orderProducts);
        return orderRepository.save(order);
    }
}
