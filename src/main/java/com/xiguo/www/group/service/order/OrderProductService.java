package com.xiguo.www.group.service.order;

import com.xiguo.www.group.repository.order.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
@Service
public class OrderProductService {
    @Autowired
    OrderProductRepository orderProductRepository;
}
