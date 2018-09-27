package com.xiguo.www.group.service.groupBuy;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.repository.groupBuy.GroupBuyRepository;
import com.xiguo.www.group.repository.product.GroupBuyProductRepository;
import com.xiguo.www.group.service.dozer.BeanConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZGC
 * @date Created in 2018/9/1 下午 5:31
 */
@Service("groupBuyService")
public class GroupBuyServiceImpl implements GroupBuyService {
    @Autowired
    BeanConvert beanConvert;

//    @Autowired
//    private EntityManager em;

    @Autowired
    GroupBuyRepository groupBuyRepository;

    @Autowired
    GroupBuyProductRepository groupBuyProductRepository;

//    @Autowired
//    GroupBuyProductRepository groupBuyProductRepository;

    @Override
    public GroupBuy saveAndUpdate(GroupBuy groupBuy, Long userId) {
        //groupBuy -> groupBuyProduct -> groupBuyProduct
        groupBuyRepository.saveAndUpdate(groupBuy, userId);
        return groupBuy;
    }

    @Override
    public GroupBuy findById(Long groupBuyId) {
        GroupBuy one = groupBuyRepository.findById(groupBuyId).get();
        return one;
    }

    @Override
    public GroupBuy findGroupBuyToGroupBuyProductImageById(Long groupBuyId) {
        return groupBuyRepository.findGroupBuyToGroupBuyProductImageById(groupBuyId);
    }


}
