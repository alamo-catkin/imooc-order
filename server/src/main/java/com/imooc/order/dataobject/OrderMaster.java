package com.imooc.order.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Catkin
 * 2020-06-25 11:21
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {

    /* 订单id */
    @Id
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

    /* 创建时间 */
    private Date createTime;

    /* 修改时间 */
    private Date updateTime;

}
