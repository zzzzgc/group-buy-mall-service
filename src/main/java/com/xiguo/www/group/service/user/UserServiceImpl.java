package com.xiguo.www.group.service.user;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.enums.SessionKey;
import com.xiguo.www.group.repository.user.UserRepository;
import com.xiguo.www.group.utils.JsonKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: ZGC
 * @date Created in 2018/9/4 下午 6:26
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${system.weChat.appid}")
    private String appid;

    @Value("${system.weChat.appsecret}")
    private String appsecret;


    @Override
    @Transactional()
    public Set<User> findAllCareAboutUserGroupBuyInfo(Long userId) {
        Set<User> allCareAboutUserGroupBuyInfo = userRepository.findAllCareAboutUserGroupBuyInfo(userId);
        return allCareAboutUserGroupBuyInfo;
    }

    @Override
    public GroupBuy findByUserId(Long userId) {
        return null;
    }

    @Override
    public Map<String, String> findPayQrCodeById(Long userId) {
        User user = userRepository.getOne(userId);
        Map<String, String> payQRCode = new HashMap<>(2);
        payQRCode.put("aliPayQrCodeUrl", user.getAliPayQrCodeUrl());
        payQRCode.put("weChatPayQrCodeUrl", user.getWeChatPayQrCodeUrl());
        return payQRCode;
    }

    @Override
    public Map login(String code, User user, HttpSession session){
        String getOpenIdUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=authorization_code";
        String responseJsonStr = restTemplate.getForObject(getOpenIdUrl, String.class);
        try {
            Map map = JsonKit.toObject(responseJsonStr, Map.class);
            System.out.println(map);
            Object errcode = map.get("errcode");
            if (errcode == null) {
                // 微信获取openId成功 = 微信登录成功
                String openId = (String) map.get("openid");
                // 本地获取user成功 = 平台登录成功
                User localUser = userRepository.findByOpenId(openId);
                if (localUser == null) {
                    // 不存在就新增,并且算是登录成功的
                    user.setOpenId(openId);
                    userRepository.save(user);
                    localUser = user;
                }
                String weChatSessionKey = (String) map.get("session_key");
                session.setAttribute(SessionKey.USER_ID.toString(), localUser.getId());
                session.setAttribute(SessionKey.WECHAT_SESSION_ID.toString(), weChatSessionKey);
                session.setAttribute(SessionKey.WECHAT_OPEN_ID.toString(), openId);
                map = new HashMap<>(2);
                map.put("sessionId", session.getId());
                map.put("userId", localUser.getId());
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
