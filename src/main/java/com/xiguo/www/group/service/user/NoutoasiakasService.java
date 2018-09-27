package com.xiguo.www.group.service.user;

import com.xiguo.www.group.entity.Noutoasiakas;
import com.xiguo.www.group.repository.user.NoutoasiakasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
public interface NoutoasiakasService {
    /**
     * 保存自提点
     *
     * @return 保存的自提点
     */
    Noutoasiakas saveNoutoasiakas(Noutoasiakas noutoasiakas);
}
