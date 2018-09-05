package com.xiguo.www.group.service.GroupBuy;

import com.xiguo.www.group.dto.GroupBuyDto;

/**
 * @author: ZGC
 * @date Created in 2018/8/26 下午 5:57
 */
public interface GroupBuyService {

    public GroupBuyDto saveAndUPdate(GroupBuyDto groupBuyDto, Long userId);

    GroupBuyDto findByUserAndId(Long userId, Long groupBuyId);

}
