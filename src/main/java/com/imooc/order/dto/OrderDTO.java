package com.imooc.order.dto;

import com.imooc.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Catkin
 * 2020-06-25 12:05
 */
@Data
public class OrderDTO {

    private String orderId;

    /* 买家姓名 */
    private String buyerName;

    /* 买家电话 */
    private String buyerPhone;

    /* 买家地址 */
    private String buyerAddress;

    /* 买家微信Openid */
    private String buyerOpenid;

    /* 订单金额 */
    private BigDecimal orderAmount;

    /* 订单状态, 默认0为新下单*/
    private Integer orderStatus;

    /* 支付状态, 默认0为未支付 */
    private Integer payStatus;

    private List<OrderDetail> orderDetailList;
}
