package com.xiguo.www.group.repository.product;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.GroupBuyProduct;
import com.xiguo.www.group.entity.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @author: ZGC
 * @date Created in 2018/9/14 下午 1:41
 */
public class OrderProductRepositoryImpl implements OrderProductInterface {

    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    public void save(GroupBuy groupBuy, List<OrderProduct> orderProducts) {
        Set<GroupBuyProduct> groupBuyProducts = groupBuy.getGroupBuyProducts();
        orderProducts.forEach(
                orderProduct -> {
                    if (orderProduct.getId() != null) {
                        // 修改商品,订正库存


                    } else {
                        // 新增商品,减库存


                    }
                }
        );
    }

    @Override
    public void delete(OrderProduct orderProduct) {
        // 删除商品,加库存

    }
}
