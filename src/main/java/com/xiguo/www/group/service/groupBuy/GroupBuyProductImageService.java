package com.xiguo.www.group.service.groupBuy;

import com.xiguo.www.group.repository.product.GroupBuyProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
@Service("groupBuyProductImageService")
public class GroupBuyProductImageService {
    @Autowired
    GroupBuyProductImageRepository groupBuyProductImagesRepository;
}
