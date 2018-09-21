package com.xiguo.www.group.controller;

import com.xiguo.www.group.entity.Order;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.enums.SessionKey;
import com.xiguo.www.group.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 订单服务
 *
 * @author: ZGC
 * @date Created in 2018/8/28 下午 12:24
 */
@Api(value="/order", tags="订单服务模块")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    @PutMapping
    @ApiOperation("保存和更新接口")
    public ResponseEntity save(@RequestBody OrderObject orderObject, HttpSession session) {
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
        orderService.save(orderObject.order, orderObject.groupBuyId, userId, orderObject.merchantUserId);
        return RETemplate.ok();
    }

    @GetMapping("/customer")
    @ApiOperation("用客户id获取所有订单")
    public ResponseEntity findByCustomerUserId(HttpSession session) {
//        Long userId = 1L;
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
        List<Order> orders = orderService.findByCustomerUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/merchant")
    @ApiOperation("用商户id获取所有订单")
    public ResponseEntity findByMerchantUserId(HttpSession session,@RequestParam int searchType,@RequestParam String searchText) {
//        Long userId = 1L;
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
        List<Order> orders = orderService.findByMerchantUserId(userId, searchType,  searchText);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/detail/{orderId}")
    @ApiOperation("获取详细订单")
    public ResponseEntity findDetailById(@PathVariable Long orderId) {
        Order order = orderService.findDetailById(orderId);
        return RETemplate.ok(order);
    }

}

class OrderObject {
    // 类中属性必须为public，或者有setter和getter；Param类中的属性只能比json中的属性多，不能少。

    public Order order;
    public Long groupBuyId;
    public Long merchantUserId;
}
