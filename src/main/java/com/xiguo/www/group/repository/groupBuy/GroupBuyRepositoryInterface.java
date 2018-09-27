package com.xiguo.www.group.repository.groupBuy;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.User;

import java.util.List;

/**
 * @author: ZGC
 * @date Created in 2018/9/2 下午 2:38
 */
public interface GroupBuyRepositoryInterface {
    /**
     * 保存或更新团购内容
     * 支持以下的级联 保存和更新
     * groupBuy <-> n
     * groupBuy <-> groupBuyProduct <-> groupBuyProductImage
     *
     * @param groupBuy 团购
     * @param userId   用户id(团购拥有者)
     * @return 团购(更新的会附带id)
     * 注意: 不可以用控制层返回.因为内部含有 级联操作需要的关联 递归对象会陷入死循环 ,比如 groupBuy <-> n <-> groupBuy <-> n .....
     */
    GroupBuy saveAndUpdate(GroupBuy groupBuy, Long userId);


    /**
     * 获取以下关联的数据
     * groupBuy -> groupBuyProduct -> groupBuyProductImage
     * 并附带自提点 groupBuy -> groupBuyN
     *
     * @param groupBuyId
     * @return
     */
    GroupBuy findGroupBuyToGroupBuyProductImageById(Long groupBuyId);

}
