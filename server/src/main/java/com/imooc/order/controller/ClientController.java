package com.imooc.order.controller;

import com.imooc.order.client.ProductClient;
import com.imooc.order.dataobject.ProductInfo;
import com.imooc.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Ribbon客户端连接测试
 * Created by Catkin
 * 2020-06-25 14:19
 */
@RestController
@Slf4j
public class ClientController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
//
//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired //Feign
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        // 1.第一种方式 (直接使用restTemplate, url写死)
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);

        // 2.第二种方式 (利用loadBalancerClient通过应用名获取url)
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url, String.class);

        // 3.第三种方式 (利用@LoadBalanced注解配置, 可在restTemplate里使用应用名字)
//        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);

        // Feign方式
        String response = productClient.productMsg();

        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList("157875196366160022", "157875227953464068"));
        log.info("response={}", productInfoList);

        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock() {
        productClient.decreaseStock(Arrays.asList(new CartDTO("157875196366160022", 2)));

        return "ok";
    }
}
