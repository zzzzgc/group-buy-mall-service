package com.xiguo.www.group.repository.order;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.Order;
import com.xiguo.www.group.entity.OrderProduct;
import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.repository.product.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
    public Order save(Order order, Long groupBuyId, Long userId, Long merchantUserId) {
        Order backupOrder = null;
        if (groupBuyId == null || userId == null || merchantUserId == null) {
            Optional<Order> byId = orderRepository.findById(order.getId());
            if (!byId.isPresent()) {
                return null;
            }
            backupOrder = byId.get();
            groupBuyId = backupOrder.getGroupBuy().getId();
            merchantUserId = backupOrder.getMerchantUser().getId();
//            // 关联团员 order -> user
//            order.setUser(backupOrder.getUser());
//            // 关联团长 order -> merchantUser
//            order.setMerchantUser(backupOrder.getMerchantUser());
//            // 关联团购 order -> groupBuy
//            order.setGroupBuy(backupOrder.getGroupBuy());
        }
        // 关联团员 order -> user
        order.setUser(new User(userId));
        // 关联团长 order -> merchantUser
        order.setMerchantUser(new User(merchantUserId));
        // 关联团购 order -> groupBuy
        order.setGroupBuy(new GroupBuy(groupBuyId));


        GroupBuy groupBuy = order.getGroupBuy();
        if (groupBuy != null) {
            // 关联团购 order <-> groupBuy
            HashSet<Order> orders = new HashSet<>();
            orders.add(order);
            groupBuy.setOrders(orders);
        }

        List<OrderProduct> orderProducts = order.getOrderProducts();
        final BigDecimal[] orderTotalPrice = {new BigDecimal(0.00)};
        if (orderProducts != null) {
            orderProducts.forEach(orderProduct -> {
                // 关联订单商品 order <-> orderProduct
                orderProduct.setOrder(order);
                BigDecimal productTotalPrice = new BigDecimal(orderProduct.getNumber()).multiply(orderProduct.getPrice());
                BigDecimal add = orderTotalPrice[0].add(productTotalPrice);
                orderTotalPrice[0] = add;
                // 关联订单商品和商品 orderProduct <-> product
                HashSet<OrderProduct> orderProductsSet = new HashSet<>();
                orderProductsSet.add(orderProduct);
            });
        }
        order.setTotalPrice(orderTotalPrice[0]);
        if (order.getId() != null) {
//            orderProduct.getGroupBuyProduct().setOrderProducts(orderProductsSet);
            // 清空订单商品
            List<OrderProduct> oldOrderProducts = orderRepository.getOne(order.getId()).getOrderProducts();
            orderProductRepository.deleteAll(oldOrderProducts);
        }
        // 覆盖订单商品
        order.setOrderProducts(orderProducts);
        //
        return orderRepository.save(order);
    }

    @Override
    public Order findDetailById(Long orderId) {
        Optional<Order> byId = orderRepository.findById(orderId);
        if (!byId.isPresent()) {
            return null;
        }
        Order order = byId.get();
        order.getGroupBuy().getGroupBuyNoutoasiakases().size();
        order.getOrderProducts().size();
//        order.getMerchantUser().getNickName();
        return order;
    }
}
