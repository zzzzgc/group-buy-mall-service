package com.xiguo.www.group.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xiguo.www.group.utils.JsonKit;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ZGC
 * @date Created in 2018/9/8 下午 12:52
 */
@Slf4j
@Service
public class WeCharService {

    @Value("${system.weChat.appid}")
    private String appid;

    @Value("${system.weChat.appsecret}")
    private String appsecret;

    private static String accessToken;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FileService fileService;

    private static String DEFAULT_CHARSET = "UTF-8";

    /**
     * 更新accessToken
     */
    void updateAccessToken() {
        log.info("update AccessToken start {}", System.currentTimeMillis());
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + appsecret;
        Map responseMap = restTemplate.getForObject(url, Map.class);
        Object newAccessToken = responseMap.get("access_token");
        if (newAccessToken == null) {
            throw new RuntimeException("获取 access_token 失败");
        }
        accessToken = (String) newAccessToken;
        log.info("update AccessToken end {} acquire success! new accessToken : {} ", System.currentTimeMillis(), getAccessToken());
    }

    /**
     * 获取微信小程序码
     * 接口B
     * 适用于需要的码数量极多的业务场景
     * 通过该接口生成的小程序码，永久有效，数量暂无限制。
     *
     * @param pagePath 小程序路径
     * @param param    携带参数
     * @return 二维码URL
     */
    public String createQrCodeByB(String pagePath, String param) {
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + WeCharService.getAccessToken();
        Map<String, Object> requestMap = new HashMap<>(2);
        // 必须是已经发布的小程序存在的页面（否则报错），例如 "pages/index/index" ,根路径前不要填加'/',不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面
        requestMap.put("page", pagePath);
        // 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
        requestMap.put("scene", param);
        String requestJson = null;
        try {
            requestJson = JsonKit.toJson(requestMap);
            System.out.println("请求数据: " + requestJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("格式化失败");
        }

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<byte[]> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(requestJson.getBytes(Charset.forName(DEFAULT_CHARSET)), headers),
                byte[].class
        );

        HttpStatus statusCode = response.getStatusCode();
        if (response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON_UTF8) || statusCode.isError()) {
            String errorMsgJson = new String(response.getBody());
            throw new RuntimeException("获取 qrCode 失败,错误信息 " + errorMsgJson);
        }
        byte[] result = response.getBody();
        String fileNameSuffix = response.getHeaders().getContentType().getSubtype();
        return fileService.saveFile(result, fileNameSuffix, "/qrCode");
    }

    public static String getAccessToken() {
        return accessToken;
    }

}
