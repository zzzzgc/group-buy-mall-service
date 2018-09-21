package com.xiguo.www.group.controller;

import com.xiguo.www.group.entity.*;
import com.xiguo.www.group.enums.GroupBuyStatus;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.enums.SessionKey;
import com.xiguo.www.group.repository.groupBuy.GroupBuyRepository;
import com.xiguo.www.group.service.dozer.BeanConvert;
import com.xiguo.www.group.service.groupBuy.GroupBuyService;
import com.xiguo.www.group.vo.GroupBuySellInfoVo;
import com.xiguo.www.group.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 团购服务
 *
 * @author: ZGC
 * @date Created in 2018/8/28 下午 12:24
 */
@Api(value = "/groupBuy", tags = "团购服务模块")
@RestController
@RequestMapping("/groupBuy")
public class GroupBuyController {

    @Autowired
    GroupBuyService groupBuyService;

    /**
     * 根据id保存.如果是包含id(离线)则保存.如果不包含id(瞬态)则新增.
     * 写操作必须加上@Modifying 在 Repository中.因为Repository默认只读
     *
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

    @PersistenceContext
    EntityManager em;

    @Autowired
    BeanConvert beanConvert;

    @GetMapping("/findAllSellInfo/{status}")
    @ApiOperation("根据用户id获取所有团购的销售信息")
    public ResponseEntity findAllSellInfo(@PathVariable("status") int status, HttpSession session) {
        Long userId = (Long) session.getAttribute(SessionKey.USER_ID.toString());
//        Long userId = 1L;
        Query nativeQuery = em.createNativeQuery("SELECT " +
                "	gb.id, " +
                "	gb.title, " +
                "	gb.create_at, " +
                "	vtpn.totalParticipantNumber, " +
                "	vtpsn.totalSellNumber, " +
                "	vtpsn.totalSellPrice, " +
                "	vtpsn.totalSellPrice / vtpn.totalParticipantNumber AS averageConsumePrice " +
                "FROM " +
                "	`user` u " +
                "JOIN group_buy gb ON gb.user_id = u.id " +
                "JOIN v_group_buy_total_participant_number vtpn ON vtpn.id = gb.id " +
                "JOIN v_group_buy_total_product_sell_info vtpsn ON vtpsn.id = gb.id " +
                "WHERE " +
                "	gb.`status` = ?1 " +
                "AND u.id = ?2 ");
        nativeQuery.setParameter(1, status);
        nativeQuery.setParameter(2, userId);
        nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List resultList = nativeQuery.getResultList();
        List<GroupBuySellInfoVo> groupBuys = beanConvert.convert(resultList, GroupBuySellInfoVo.class);
        for (GroupBuySellInfoVo groupBuyVos : groupBuys) {
            Long id = groupBuyVos.getId();
            GroupBuy groupBuy = groupBuyRepository.getOne(id);
            Set<GroupBuyProduct> groupBuyProducts = groupBuy.getGroupBuyProducts();
            ArrayList<ProductVo> productVos = new ArrayList<>();
            for (GroupBuyProduct product : groupBuyProducts) {
                ProductVo productVo = new ProductVo();
                productVo.setName(product.getName());
                productVo.setPrice(product.getPrice());
                productVo.setSellTotalNumber(product.getSellTotalNumber());
                productVos.add(productVo);
            }
            groupBuyVos.setProducts(productVos);
            groupBuyVos.setCreateAt(groupBuy.getCreateAt());
        }
        return RETemplate.ok(groupBuys);
    }


}
