package com.xiguo.www.group.controller;

import com.xiguo.www.group.repository.groupBuy.*;
import com.xiguo.www.group.repository.order.OrderProductRepository;
import com.xiguo.www.group.repository.order.OrderRepository;
import com.xiguo.www.group.repository.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 临时汇总服务
 * @author: ZGC
 * @date Created in 2018/8/27 下午 1:01
 */
@RestController
@RequestMapping("/temp")
public class TemporaryController {
    @Autowired
    private GroupBuyNoutoasiakasRepository groupBuyNoutoasiakasRepository;
    @Autowired
    private GroupBuyProductRepository groupBuyProductRepository;
    @Autowired
    private GroupBuyProductImageRepository groupBuyProductImagesRepository;
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

    /**
     * 取参方式
     *
     * 1 @PathVariable
     * 获取路径参数。即url/{id}这种形式。
     *
     * 2 @RequestParam
     * 获取查询参数。即url?name=这种形式
     *
     * 3 @RequestBody
     * 获取文件体携带内容.
     *
     * 4 直接接受对象或者Map.会根据对象的setting注入参数
     * 加上其他注解也可以被注入对应的参数
     *
     * 5 其他
     *  @RequestHeader
     *  获取文件头
     *
     *  @CookieValue
     *  获取cookie
     *
     *  直接HttpServletRequest dto
     *  取HttpServletRequest对象
     */

}
