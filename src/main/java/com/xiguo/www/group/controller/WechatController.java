package com.xiguo.www.group.controller;

import com.xiguo.www.group.repository.groupBuy.*;
import com.xiguo.www.group.repository.order.OrderProductRepository;
import com.xiguo.www.group.repository.order.OrderRepository;
import com.xiguo.www.group.repository.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信服务
 * @author: ZGC
 * @date Created in 2018/8/28 下午 12:24
 */
@RestController("/wechat")
public class WechatController {

    @Autowired
    private GroupBuyNoutoasiakasRepository groupBuyNoutoasiakasRepository;
    @Autowired
    private GroupBuyProductRepository groupBuyProductRepository;
    @Autowired
    private GroupBuyProductImageRepository groupBuyProductsImagesRepository;
    @Autowired
    private GroupBuyRepository groupBuyRepository;
    @Autowired
    private NoutoasiakasRepository noutoasiakasRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserCareAboutGroupBuyRepository userCareAboutGroupBuyRepository;
    @Autowired
    private UserCustomerDefaultSettingRepository userCustomerDefaultSettingRepository;
    @Autowired
    private UserMerchantDefaultSettingRepository userMerchantDefaultSettingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserShopRepository userShopRepository;

//    @GetMapping("/{id}")
//    public GroupBuyDto getGroupBuy(@PathVariable Long id) {
//        return groupBuyRepository.getOne(id);
//    }

}
