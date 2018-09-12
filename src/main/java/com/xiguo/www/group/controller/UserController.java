package com.xiguo.www.group.controller;

import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.entity.UserShop;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.enums.SessionKey;
import com.xiguo.www.group.repository.user.UserRepository;
import com.xiguo.www.group.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

/**
 * 用户服务
 *
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:59
 */
@Api(value="/user", tags="用户服务模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private Environment env;


    @GetMapping("/login")
    @ApiOperation(value="登录接口")
    public ResponseEntity login(@RequestParam String code, User user, HttpSession session) {
        Map login = userService.login(code, user, session);
        if (login != null){
            return RETemplate.ok(login);
        }
        return RETemplate.reject("登录失败,请重新登录");
    }

    /**
     * /user/allCareAboutUserGroupBuyInfo
     * @param session session
     * @return 附带各种参数的对象
     */
    @GetMapping("/allCareAboutUserGroupBuyInfo")
    @ApiOperation(value="根据关注的团长,获取他们的团购信息")
    public ResponseEntity findAllCareAboutUserGroupBuyInfo(HttpSession session) {
//        Long userId= 1L;
        Long userId= (Long) session.getAttribute(SessionKey.USER_ID.toString());
        Set<User> user = userService.findAllCareAboutUserGroupBuyInfo(userId);
        return RETemplate.ok(user);
    }

    /**
     * 根据订单id获取支付二维码
     * @param userId 订单id
     * @return 二维码集合
     * {
     *      weCharQS: 'https://xxxx',
     *      aliPayQS: 'https://xxxx'
     * }
     */
    @GetMapping("/payQrCodeById/{userId}")
    public ResponseEntity findPayQrCodeByOrderId(@PathVariable Long userId) {
        Map<String,String> payQRCode = userService.findPayQrCodeById(userId);
        return RETemplate.ok(payQRCode);
    }

    /**
     * 获取用户信息和店铺信息
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity toUserShop(@PathVariable Long userId){
        User user = userRepository.getOne(userId);
        UserShop userShop = user.getUserShop();
        if (userShop!= null) {
            userShop.getPhone();
        }
        return RETemplate.ok(user);
    }
}
