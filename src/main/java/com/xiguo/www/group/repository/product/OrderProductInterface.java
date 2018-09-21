package com.xiguo.www.group.repository.product;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.OrderProduct;

import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/9/14 下午 1:39
 */
public interface OrderProductInterface {
    /**
     * 保存订单的商品
     */
    void save(GroupBuy groupBuy, List<OrderProduct> orderProducts);

    /**
     * 删除订单的商品
     * @param orderProduct
     */
    void delete(OrderProduct orderProduct);
}
