package com.xiguo.www.group.controller;

import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.entity.UserAuthentication;
import com.xiguo.www.group.entity.UserCareAboutGroupBuy;
import com.xiguo.www.group.entity.UserShop;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.enums.SessionKey;
import com.xiguo.www.group.repository.user.UserAuthenticationRepository;
import com.xiguo.www.group.repository.user.UserRepository;
import com.xiguo.www.group.service.WeCharService;
import com.xiguo.www.group.service.user.UserCareAboutGroupBuyService;
import com.xiguo.www.group.service.user.UserService;
import com.xiguo.www.group.utils.JsonKit;
import com.xiguo.www.group.utils.Security.AES;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 用户服务
 *
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:59
 */
@Api(value = "/user", tags = "用户服务模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserCareAboutGroupBuyService userCareAboutGroupBuyService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private Environment env;

    @Autowired
    WeCharService weCharService;


    @GetMapping("/login")
    @ApiOperation(value = "一级登录接口-通用")
    public ResponseEntity login(@RequestParam String code, User user, HttpSession session) {
        Map login = userService.login(code, user, session);
        if (login != null) {
            return RETemplate.ok(login);
        }
        return RETemplate.reject("登录失败,请重新登录");
    }

    /**
     * 手机号码登录(临时)
     *
     * @return
     */
    @GetMapping("/login/merchant")
    @ApiOperation(value = "二级登录接口-商户手机")
    public ResponseEntity merchantPhoneLogin(@RequestParam String iv, @RequestParam String encryptedData, HttpSession session) {
        String sessionKey = (String) session.getAttribute(SessionKey.WECHAT_SESSION_KEY.toString());
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
        // 后来可能改为通过完整版用户认证 UserAuthentication 来判断用户是否有权,现在还是直接帮用户完善部分 UserAuthentication ,来实现.比如临时完善手机号码并校验是否有权登录
        try {
            System.out.println("解密需要:" +
                    "sessionKey:" + sessionKey +
                    " ,encryptedData:" + encryptedData +
                    " ,iv:" + iv
            );
            // 接收的时候encryptedData的 '+' 被转义成了 ' '.需要转换回来
            encryptedData = encryptedData.replace(" ","+");
            iv = iv.replace(" ","+");

            byte[] keyByte = Base64.getDecoder().decode(sessionKey);
            byte[] dataByte = Base64.getDecoder().decode(encryptedData);
            byte[] ivByte = Base64.getDecoder().decode(iv);
            System.out.println("解密信息:" +
                    "dataByte:" + dataByte +
                    " ,keyByte:" + keyByte +
                    " ,ivByte:" + ivByte
            );
            byte[] decrypt = AES.decrypt(dataByte, keyByte, ivByte);
            String content = new String(Objects.requireNonNull(decrypt), StandardCharsets.UTF_8);
            System.out.println("结果:" + content);
            HashMap hashMap = JsonKit.toObject(content, HashMap.class);
            String phoneNumber = (String) hashMap.get("phoneNumber");
            UserAuthentication userAuthentication = userAuthenticationRepository.findByUser_id(userId);
            if (userAuthentication == null) {
                // 帮助用户添加认证信息
                userAuthentication = new UserAuthentication();
                userAuthentication.setPhone(phoneNumber);
                userAuthentication.setUser(new User(userId));
                userAuthenticationRepository.save(userAuthentication);
            }
            int openGroupPermissions = userAuthentication.getOpenGroupPermissions();
            if (openGroupPermissions == 0) {
                return RETemplate.failure("认证信息在审核,请稍后再试");
            }
            return RETemplate.ok();
        } catch (IOException e) {
            e.printStackTrace();
            return RETemplate.failure("数据异常,请稍后重试");
        }
    }

    /**
     * /user/allCareAboutUserGroupBuyInfo
     *
     * @param session session
     * @return 附带各种参数的对象
     */
    @GetMapping("/allCareAboutUserGroupBuyInfo")
    @ApiOperation(value = "根据关注的团长,获取他们的团购信息")
    public ResponseEntity findAllCareAboutUserGroupBuyInfo(HttpSession session) {
//        Long userId= 1L;
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
        Set<User> user = userService.findAllCareAboutUserGroupBuyInfo(userId);
        return RETemplate.ok(user);
    }

    /**
     * 根据订单id获取支付二维码
     *
     * @param userId 订单id
     * @return 二维码集合
     * {
     * weCharQS: 'https://xxxx',
     * aliPayQS: 'https://xxxx'
     * }
     */
    @GetMapping("/payQrCodeById/{userId}")
    public ResponseEntity findPayQrCodeByOrderId(@PathVariable Long userId) {
        Map<String, String> payQRCode = userService.findPayQrCodeById(userId);
        return RETemplate.ok(payQRCode);
    }

    /**
     * 获取用户信息和店铺信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity toUserShop(@PathVariable Long userId) {
        User user = userRepository.getOne(userId);
        UserShop userShop = user.getUserShop();
        if (userShop != null) {
            userShop.getPhone();
        }
        return RETemplate.ok(user);
    }

    /**
     * 保存关注的用户
     *
     * @param careAboutUserId 关注的用户id
     * @param session         session
     * @return 成功状态
     */
    @PostMapping("/careAboutUser/{careAboutUserId}")
    public ResponseEntity saveCareAboutUser(@PathVariable Long careAboutUserId, HttpSession session) {
//        Long userId = 1L;
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
        UserCareAboutGroupBuy careAboutUserByCareAboutUserId = userCareAboutGroupBuyService.findCareAboutUserByCareAboutUserId(userId, careAboutUserId);
        if (careAboutUserByCareAboutUserId == null) {
            userCareAboutGroupBuyService.saveCareAboutUser(userId, careAboutUserId);
        }
        return RETemplate.ok();
    }
}
