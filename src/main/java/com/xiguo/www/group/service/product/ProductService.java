package com.xiguo.www.group.service.product;

import com.xiguo.www.group.repository.product.GroupBuyProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/9/14 下午 3:58
 */
@Service
public class ProductService {

    @Autowired
    GroupBuyProductRepository groupBuyProductRepository;

    /**
     * 更新商品库存
     * <p>
     * 旧-新 = 销售数量的减少数量 |不需要
     * 2-2=0 不变
     * 2-6=-4 需要增加4
     * 6-2=4 需要减少4
     * <p>
     * 新-旧 = 销售数量的新增数量 |需要
     * 2-2=0 不变
     * 2-6=-4 需要减少4
     * 6-2=4 需要增加4
     *
     * @param productId 商品id
     * @param number    增加的数量 这里需要的是 ->新增数量<- ,而不是减少数量
     * @return 0没更新  1更新成功 >1异常
     */
    public int updateInventory(Long productId, int number) {
        return groupBuyProductRepository.updateInventory(productId, number);
    }

    /**
     * 更新商品库存
     * <p>
     * 会把之后的值减去之前的值求出 库存需要增加的量
     *
     * @param productId    商品id
     * @param beforeNumber 该商品订单之前需要的数量
     * @param afterNumber  该商品订单之后需要的数量
     * @return 0没更新  1更新成功 >1异常
     */
    public int updateInventory(Long productId, int beforeNumber, int afterNumber) {
        // TODO 日志记录
        return groupBuyProductRepository.updateInventory(productId, afterNumber - beforeNumber);
    }
}
