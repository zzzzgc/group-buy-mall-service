package com.xiguo.www.group.controller;

import com.xiguo.www.group.entity.GroupBuyProductImage;
import com.xiguo.www.group.enums.RETemplate;
import com.xiguo.www.group.repository.product.GroupBuyProductImageRepository;
import com.xiguo.www.group.repository.product.GroupBuyProductRepository;
import com.xiguo.www.group.service.dozer.BeanConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 商品服务
 *
 * @author: ZGC
 * @date Created in 2018/8/28 下午 12:24
 */
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
    public ResponseEntity deleteProductImage(@PathVariable Long groupBuyProductId) {
        GroupBuyProductImage groupBuyProductImage = groupBuyProductsImagesRepository.getOne(groupBuyProductId);
        groupBuyProductsImagesRepository.delete(groupBuyProductImage);
        return RETemplate.ok();
    }
}
