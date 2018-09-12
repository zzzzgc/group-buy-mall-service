package com.xiguo.www.group.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xiguo.www.group.utils.JsonKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
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

    String DEFAULT_CHARSET = "UTF-8";

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
     * 获取微信小程序码 接口B
     *
     * @param pagePath 小程序路径
     * @param param    携带参数
     * @return 二维码URL
     */
    public String createQrCodeByB(String pagePath, String param) {
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + WeCharService.getAccessToken();
        Map<String, Object> requestMap = new HashMap<>(2);
        requestMap.put("page", pagePath);
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
        return fileService.saveFile(result, response.getHeaders().getContentType().getSubtype(), "/qrCode");
    }

    public static String getAccessToken() {
        return accessToken;
    }
}
