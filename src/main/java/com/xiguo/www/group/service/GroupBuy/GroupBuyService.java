package com.xiguo.www.group.service.GroupBuy;

import com.xiguo.www.group.entity.GroupBuy;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
public interface GroupBuyService {

    public GroupBuy saveAndUpdate(GroupBuy groupBuyDto, Long userId);

    GroupBuy findById(Long groupBuyId);

    GroupBuy findGroupBuyToGroupBuyProductImageById (Long groupBuyId);

}
