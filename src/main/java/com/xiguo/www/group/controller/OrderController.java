package com.xiguo.www.group.controller;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.Order;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.enums.SessionKey;
import com.xiguo.www.group.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 订单服务
 *
 * @author: ZGC
 * @date Created in 2018/8/28 下午 12:24
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    @PutMapping
    public ResponseEntity save(@RequestBody OrderObject orderObject, HttpSession session) {
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
        orderObject.order.setGroupBuy(new GroupBuy(orderObject.groupBuyId));
        orderService.save(orderObject.order, userId, orderObject.merchantUserId);
        return RETemplate.ok();
    }

//    public Order

}

class OrderObject {
    // 类中属性必须为public，或者有setter和getter；Param类中的属性只能比json中的属性多，不能少。

    public Order order;
    public Long groupBuyId;
    public Long merchantUserId;
}
