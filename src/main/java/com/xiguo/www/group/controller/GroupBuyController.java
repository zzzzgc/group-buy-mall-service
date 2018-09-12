package com.xiguo.www.group.controller;

import com.xiguo.www.group.entity.GroupBuy;
import com.xiguo.www.group.entity.GroupBuyProduct;
import com.xiguo.www.group.enums.GroupBuyStatus;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.enums.SessionKey;
import com.xiguo.www.group.repository.groupBuy.GroupBuyRepository;
import com.xiguo.www.group.service.GroupBuy.GroupBuyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 团购服务
 * @author: ZGC
 * @date Created in 2018/8/28 下午 12:24
 */
@Api(value="/groupBuy", tags="团购服务模块")
@RestController
@RequestMapping("/groupBuy")
public class GroupBuyController {

    @Autowired
    GroupBuyService groupBuyService;

    /**
     * 根据id保存.如果是包含id(离线)则保存.如果不包含id(瞬态)则新增.
     * 写操作必须加上@Modifying 在 Repository中.因为Repository默认只读
     * @param groupBuy
     * @return
     */
    @PostMapping
    @PutMapping
    @ApiOperation("保存和更新接口")
    public ResponseEntity saveAndUpdate(@RequestBody GroupBuy groupBuy, HttpSession session) {
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
        groupBuy = groupBuyService.saveAndUpdate(groupBuy, userId);
        // 嵌套存入的.不适合取出来因为jackson会陷入无尽的递归groupBuyNoutoasiakases <=> groupBuy <=> groupBuyNoutoasiakases... (一个个删比较麻烦而且不通用)
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Autowired
    GroupBuyRepository groupBuyRepository;

    @GetMapping("/{groupBuyId}")
    @ApiOperation("获取接口")
    public ResponseEntity get(@PathVariable Long groupBuyId) {
        GroupBuy groupBuy = groupBuyService.findById(groupBuyId);
        groupBuy.getGroupBuyNoutoasiakases().size();
        return RETemplate.ok(groupBuy);
    }

    @GetMapping("/toGroupBuyProductImage/{groupBuyId}")
    @ApiOperation("根据团购id获取 groupBuy -> groupBuyProduct - > groupBuyProductImage")
    public ResponseEntity findGroupBuyToGroupBuyProductImageById(@PathVariable("groupBuyId") Long groupBuyId) {
        GroupBuy groupBuy = groupBuyRepository.getOne(groupBuyId);
        groupBuy.getGroupBuyNoutoasiakases().size();
        for (GroupBuyProduct groupBuyProduct : groupBuy.getGroupBuyProducts()) {
            groupBuyProduct.getGroupBuyProductImages().size();
        }
        return RETemplate.ok(groupBuy);
    }

    @GetMapping("/toGroupBuyProductImage/userId/{userId}")
    @ApiOperation("根据用户id获取 groupBuy -> groupBuyProduct - > groupBuyProductImage")
    public ResponseEntity findGroupBuyToGroupBuyProductImageByUserId(@PathVariable("userId") Long userId) {
        ArrayList<Integer> status = new ArrayList<>();
        status.add(GroupBuyStatus.START.getStatus());
        List<GroupBuy> groupBuys = groupBuyRepository.findByUser_IdAndStatusIn(userId, status);
        for (GroupBuy groupBuy : groupBuys) {
            for (GroupBuyProduct groupBuyProduct : groupBuy.getGroupBuyProducts()) {
                groupBuyProduct.getGroupBuyProductImages().size();
            }
        }
        return RETemplate.ok(groupBuys);
    }


}
