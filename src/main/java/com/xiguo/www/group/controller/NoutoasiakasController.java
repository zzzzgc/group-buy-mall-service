package com.xiguo.www.group.controller;

import com.xiguo.www.group.entity.Noutoasiakas;
import com.xiguo.www.group.entity.User;
import com.xiguo.www.group.enums.SessionKey;
import com.xiguo.www.group.service.user.NoutoasiakasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: ZGC
 * @date Created in 2018/8/31 上午 10:53
 */
@RestController
public class NoutoasiakasController {

    @Autowired
    NoutoasiakasService noutoasiakasService;

    /**
     * 保存自提点
     * @param noutoasiakas 自提点
     * @return 自提点
     */
    @PostMapping("/noutoasiakas")
    public ResponseEntity saveNoutoasiakas(@RequestBody Noutoasiakas noutoasiakas, HttpSession session) {
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
        noutoasiakas.setUser(new User(userId));
        Noutoasiakas noutoasiakas1 = noutoasiakasService.saveNoutoasiakas(noutoasiakas);
        return new ResponseEntity<>(noutoasiakas1, HttpStatus.OK);
    }
}
