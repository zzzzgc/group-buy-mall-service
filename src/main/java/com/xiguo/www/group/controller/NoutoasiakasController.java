package com.xiguo.www.group.controller;

import com.xiguo.www.group.entity.Noutoasiakas;
import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.enums.SessionKey;
import com.xiguo.www.group.service.user.NoutoasiakasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 上午 10:53
 */
@Api(value = "/noutoasiakas", tags = "自提点服务模块")
@RestController
public class NoutoasiakasController {

    @Autowired
    NoutoasiakasService noutoasiakasService;

    /**
     * 保存自提点
     *
     * @param noutoasiakas 自提点
     * @return 自提点
     */
    @ApiOperation("保存和更新")
    @PostMapping("/noutoasiakas")
    public ResponseEntity saveNoutoasiakas(@RequestBody Noutoasiakas noutoasiakas, HttpSession session) {
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
        noutoasiakas.setUser(new User(userId));
        Noutoasiakas noutoasiakas1 = noutoasiakasService.saveNoutoasiakas(noutoasiakas);
        return RETemplate.ok(noutoasiakas1);
    }
}
