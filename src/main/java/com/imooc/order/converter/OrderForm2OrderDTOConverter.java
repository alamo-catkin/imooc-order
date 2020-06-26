package com.imooc.order.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.imooc.order.dataobject.OrderDetail;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.enums.ResultEnum;
import com.imooc.order.exception.OrderException;
import com.imooc.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catkin
 * 2020-06-25 13:22
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    /**
     * OrderForm 2 OrderDTO
     * @param orderForm
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>() {}.getType());
        } catch (Exception e) {
            log.error("【json转换】错误, String={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
