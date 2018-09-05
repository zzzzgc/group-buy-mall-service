package com.xiguo.www.group.service.GroupBuy;

import com.xiguo.www.group.dto.GroupBuyDto;
import com.xiguo.www.group.entity.*;
import com.xiguo.www.group.repository.groupBuy.GroupBuyProductRepository;
import com.xiguo.www.group.repository.groupBuy.GroupBuyRepository;
import com.xiguo.www.group.service.dozer.BeanConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author: ZGC
 * @date Created in 2018/9/1 下午 5:31
 */
@Service("groupBuyService")
public class GroupBuyServiceImpl implements GroupBuyService {
    @Autowired
    BeanConvert beanConvert;

    @Autowired
    private EntityManager em;

    @Autowired
    GroupBuyRepository groupBuyRepository;

    @Autowired
    GroupBuyProductRepository groupBuyProductRepository;

//    @Autowired
//    GroupBuyProductRepository groupBuyProductRepository;

    @Override
    public GroupBuyDto saveAndUPdate(GroupBuyDto groupBuyDto, Long userId) {
        //groupBuy -> groupBuyProduct -> groupBuyProduct
        GroupBuy groupBuy = beanConvert.convert(groupBuyDto, GroupBuy.class);
        groupBuyRepository.saveAndUpdate(groupBuy, userId);
        groupBuyDto = beanConvert.convert(groupBuy, GroupBuyDto.class);
        return groupBuyDto;
    }

    /**
     * 获取groupBuy product image noutoasiakas
     *
     * @param userId
     * @param groupBuyId
     * @return
     */
    @Override
    public GroupBuyDto findByUserAndId(Long userId, Long groupBuyId) {
        User user = new User(userId);
        em.flush();
        GroupBuy groupBuy = groupBuyRepository.findByUserAndId(user, groupBuyId);
        groupBuy.getGroupBuyProducts().size();
        groupBuy.getGroupBuyNoutoasiakases().size();
        em.close();
        return beanConvert.convert(groupBuy, GroupBuyDto.class);
    }
}
