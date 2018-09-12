package com.xiguo.www.group.repository.groupBuy;

import com.xiguo.www.group.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author: ZGC
 * @date Created in 2018/9/2 下午 2:38
 */
public class GroupBuyRepositoryImpl implements GroupBuyRepositoryInterface {

    @Autowired
    GroupBuyRepository groupBuyRepository;


    @Override
    public GroupBuy saveAndUpdate(GroupBuy groupBuy, Long userId) {
        Set<GroupBuyProduct> groupBuyProducts = groupBuy.getGroupBuyProducts();

        Set<GroupBuyNoutoasiakas> groupBuyNoutoasiakases = groupBuy.getGroupBuyNoutoasiakases();
        if (groupBuyNoutoasiakases != null) {
            groupBuyNoutoasiakases.forEach(gBNoutoasiakas -> gBNoutoasiakas.setGroupBuy(groupBuy));
        }

        if (groupBuyProducts != null) {
            groupBuyProducts.forEach(
                    groupBuyProduct -> {
                        // groupBuyProduct -> GroupBuy
                        groupBuyProduct.setGroupBuy(groupBuy);
                        Set<GroupBuyProductImage> groupBuyProductImages = groupBuyProduct.getGroupBuyProductImages();
                        if (groupBuyProductImages != null) {
                            groupBuyProductImages.forEach(images -> images.setGroupBuyProduct(groupBuyProduct));
                        }
                    }
            );
        }

        groupBuy.setUser(new User(userId));
        // GroupBuy <=> groupBuyNoutoasiakas
        // User <- GroupBuy <-> GroupBuyProduct -> groupBuyProductImage 因为user是已存在的,所以只用单向关联
        groupBuyRepository.save(groupBuy);
        return groupBuy;
    }

    @Override
    public GroupBuy findGroupBuyToGroupBuyProductImageById(Long groupBuyId) {
        Optional<GroupBuy> byId = groupBuyRepository.findById(groupBuyId);
        if (!byId.isPresent()) {
            // 抛异常?
            return null;
        }
        GroupBuy one = byId.get();
        one.getGroupBuyNoutoasiakases().size();
        // groupBuyProducts 自带 image
        one.getGroupBuyProducts().size();
        one.getTitle();
        return one;
    }

}
