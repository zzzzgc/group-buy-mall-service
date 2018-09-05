package com.xiguo.www.group.service.GroupBuy;

import com.xiguo.www.group.repository.groupBuy.GroupBuyProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
@Service("groupBuyProductService")
public class GroupBuyProductService {
    @Autowired
    GroupBuyProductRepository groupBuyProductRepository;
}
