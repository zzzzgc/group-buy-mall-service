package com.xiguo.www.group.controller;

import com.xiguo.www.group.dto.UserDto;
import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.enums.SessionKey;
import com.xiguo.www.group.repository.user.UserRepository;
import com.xiguo.www.group.service.dozer.BeanConvert;
import com.xiguo.www.group.service.user.UserService;
import com.xiguo.www.group.utils.JsonKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户服务
 *
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:59
 */
@Api(value="/user", tags="订单统一服务模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    BeanConvert beanConvert;

    @Autowired
    private Environment env;

    @Value("${system.wechat.appid}")
    private String appid;

    @Value("${system.wechat.appsecret}")
    private String appsecret;

    @ApiOperation(value="登录接口", notes = "登陆接口")
    @GetMapping("/login")
    public ResponseEntity login(@RequestParam String code, User user, HttpSession session) {
//        String appid = env.getProperty("system.wechat.appid");
//        String appsecret = env.getProperty("system.wechat.appsecret");
        String getOpenIdUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=authorization_code";
        // {session_key=gDHErUz1KOIfs8DWclV5FQ==, expires_in=7200, openid=oKSQe0eCwnMnjMCPvWzMziNtJ-KY}
        try {
            // 模拟登录(测试用)
            session.setAttribute(SessionKey.USER_ID.toString(), 1L);

            HashMap<String, Object> map = new HashMap<>();
            map.put("sessionId", session.getId());
            map.put("userId", 1L);
            return new ResponseEntity<>(map, HttpStatus.OK);
//
//            String responseJsonStr = restTemplate.getForObject(getOpenIdUrl, String.class);
//            Map map = JsonKit.toObject(responseJsonStr, Map.class);
//            System.out.println(map);
//            Object errcode = map.get("errcode");
//            if (errcode == null) {
//                // 微信获取openId成功 = 微信登录成功
//                String openId = (String) map.get("openid");
//                // 本地获取user成功 = 平台登录成功
//                User localUser = userRepository.findByOpenId(openId);
//                if (localUser == null) {
//                    // 不存在就新增,并且算是登录成功的
//                    user.setOpenId(openId);
//                    userRepository.save(user);
//                    localUser = user;
//                }
//                String wechatSessionKey = (String) map.get("session_key");
//                session.setAttribute(SessionKey.USER_ID.toString(), localUser.getId());
//                session.setAttribute(SessionKey.WECHAT_SESSION_ID.toString(), wechatSessionKey);
//                session.setAttribute(SessionKey.WECHAT_OPEN_ID.toString(), openId);
//
//                map = new HashMap<>(2);
//                map.put("sessionId", session.getId());
//                map.put("userId", 1L);
//                return new RETemplate<>(map, HttpStatus.OK);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("登录失败,请重新登录", HttpStatus.FORBIDDEN);
    }

    /**
     * /user/allCareAboutUserGroupBuyInfo
     * @param session session
     * @return 附带各种参数的对象
     */
    @GetMapping("/allCareAboutUserGroupBuyInfo")
    public ResponseEntity findAllCareAboutUserGroupBuyInfo(HttpSession session) {
//        Long userId= 1L;
        Long userId= (Long) session.getAttribute(SessionKey.USER_ID.toString());
        Set<User> user = userService.findAllCareAboutUserGroupBuyInfo(userId);
        return RETemplate.ok(user);
    }

    @GetMapping("/findByUserId/{userId}")
    public ResponseEntity findByUserId(@PathVariable Long userId) {
        GroupBuy groupBuy = userService.findByUserId(userId);
        return RETemplate.ok(groupBuy);
    }
}
