package com.xiguo.www.group.service.user;

import com.xiguo.www.group.entity.Noutoasiakas;
import com.xiguo.www.group.repository.user.NoutoasiakasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
@Service("noutoasiakasService")
public class NoutoasiakasServiceImpl implements NoutoasiakasService {
    @Autowired
    NoutoasiakasRepository noutoasiakasRepository;

    @Override
    public Noutoasiakas saveNoutoasiakas(Noutoasiakas noutoasiakas) {
        return noutoasiakasRepository.save(noutoasiakas);
    }
}
