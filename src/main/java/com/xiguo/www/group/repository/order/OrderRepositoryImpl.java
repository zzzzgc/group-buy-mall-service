package com.xiguo.www.group.repository.order;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.Order;
import com.xiguo.www.group.entity.OrderProduct;
import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.repository.product.OrderProductRepository;
import com.xiguo.www.group.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: ZGC
 * @date Created in 2018/9/5 下午 4:20
 */
public class OrderRepositoryImpl implements OrderInterface {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    ProductService productService;

    @Override
    @Modifying
    public Order save(Order order, Long groupBuyId, Long userId, Long merchantUserId) {
        Map<Long, OrderProduct> oldOrderProductMap = null;
        if (order.getId() != null) {
            // 如果是 修改订单则获取他之前的所有订单商品信息
            List<OrderProduct> oldOrderProducts = orderRepository.getOne(order.getId()).getOrderProducts();
            oldOrderProductMap = oldOrderProducts.stream().collect(Collectors.toMap(OrderProduct::getId, Function.identity()));
        }

        Order backupOrder = null;
        if (groupBuyId == null || userId == null || merchantUserId == null) {
            Optional<Order> byId = orderRepository.findById(order.getId());
            if (!byId.isPresent()) {
                return null;
            }
            backupOrder = byId.get();
            groupBuyId = backupOrder.getGroupBuy().getId();
            merchantUserId = backupOrder.getMerchantUser().getId();
        }
        order.setUser(new User(userId));
        order.setMerchantUser(new User(merchantUserId));
        order.setGroupBuy(new GroupBuy(groupBuyId));

        GroupBuy groupBuy = order.getGroupBuy();
        if (groupBuy != null) {
            HashSet<Order> orders = new HashSet<>();
            orders.add(order);
            groupBuy.setOrders(orders);
        }

        // 不会支持删除操作的,避免过于复杂.
        List<OrderProduct> orderProducts = order.getOrderProducts();
        BigDecimal orderTotalPrice = new BigDecimal(0.00);
        if (orderProducts != null) {
            for (OrderProduct orderProduct : orderProducts) {
                // 商品是否已下单
                if (orderProduct.getId() != null) {
                    OrderProduct oldOrderProduct = oldOrderProductMap.get(orderProduct.getId());
                    // 订正库存数量
                    if (productService.updateInventory(orderProduct.getGroupBuyProduct().getId(), oldOrderProduct.getNumber(), orderProduct.getNumber()) != 1) {
                        throw new RuntimeException("提交订单失败,商品 " + orderProduct.getName() + " 的库存不足"+orderProduct.getNumber()+"个,请减少数量后重试.");
                    }
                } else {
                    // 划扣库存数量,初始商品数量为0
                    if (productService.updateInventory(orderProduct.getGroupBuyProduct().getId(), 0, orderProduct.getNumber()) != 1) {
                        throw new RuntimeException("提交订单失败,商品 " + orderProduct.getName() + " 的库存不足"+orderProduct.getNumber()+"个,请减少数量后重试.");
                    }
                }
                orderProduct.setOrder(order);
                BigDecimal productTotalPrice = new BigDecimal(orderProduct.getNumber()).multiply(orderProduct.getPrice());
                orderTotalPrice = orderTotalPrice.add(productTotalPrice);
            }
        }
        order.setTotalPrice(orderTotalPrice);
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
