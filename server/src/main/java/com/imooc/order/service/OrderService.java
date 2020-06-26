package com.imooc.order.service;

import com.imooc.order.dto.OrderDTO;

/**
 * Created by Catkin
 * 2020-06-25 12:04
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
