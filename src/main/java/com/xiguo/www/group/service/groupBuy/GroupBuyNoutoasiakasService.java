package com.xiguo.www.group.service.groupBuy;

import com.xiguo.www.group.repository.groupBuy.GroupBuyNoutoasiakasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
@Service("groupBuyNoutoasiakas")
public class GroupBuyNoutoasiakasService {
    @Autowired
    GroupBuyNoutoasiakasRepository groupBuyNoutoasiakasRepository;
}
