package com.imooc.order.client;

import com.imooc.order.dataobject.ProductInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Ribbon负载均衡Feign声明
 * Created by Catkin
 * 2020-06-25 16:04
 */
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfo> listProduct(List<String> productIdList);
}
