package com.xiguo.www.group.controller;

import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.service.WeCharService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信服务
 *
 * @author: ZGC
 * @date Created in 2018/8/28 下午 12:24
 */
@Api(value="/weChat", tags="微信服务模块")
@RestController
@RequestMapping("/weChat")
public class WechatController {

    @Autowired
    WeCharService weCharService;

    @PostMapping("/qrCode")
    @ApiOperation("二维码生成接口")
    public ResponseEntity getQrCode(String pagePath, String param) {
        String qrCodeByB = weCharService.createQrCodeByB(pagePath, param);
        return RETemplate.ok(qrCodeByB);
    }

}
