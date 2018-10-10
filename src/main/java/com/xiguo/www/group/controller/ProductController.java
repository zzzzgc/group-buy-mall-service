package com.xiguo.www.group.controller;

import com.xiguo.www.group.entity.GroupBuyProduct;
import com.xiguo.www.group.entity.GroupBuyProductImage;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.repository.product.GroupBuyProductImageRepository;
import com.xiguo.www.group.repository.product.GroupBuyProductRepository;
import com.xiguo.www.group.service.dozer.BeanConvert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 商品服务
 *
 * @author: ZGC
 * @date Created in 2018/8/28 下午 12:24
 */
@Api(value = "/product", tags = "商品服务模块")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    GroupBuyProductRepository productRepository;

    @Autowired
    GroupBuyProductImageRepository groupBuyProductsImagesRepository;

    @Autowired
    BeanConvert beanConvert;

    @DeleteMapping("/image/{groupBuyProductId}")
    @ApiOperation("删除图片接口")
    public ResponseEntity deleteProductImage(@PathVariable Long groupBuyProductId) {
        GroupBuyProductImage groupBuyProductImage = groupBuyProductsImagesRepository.getOne(groupBuyProductId);
        groupBuyProductsImagesRepository.delete(groupBuyProductImage);
        return RETemplate.ok();
    }

    @GetMapping("/toGroupBuyProductImageById/{groupBuyProductId}")
    @ApiOperation("获取商品信息")
    public ResponseEntity toGroupBuyProductImageById(@PathVariable Long groupBuyProductId) {
        Optional<GroupBuyProduct> byId = productRepository.findById(groupBuyProductId);
        if (byId.isPresent()) {
            return RETemplate.ok(byId.get());
        }
        return RETemplate.failure();
    }
}
