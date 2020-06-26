package com.imooc.order.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Catkin
 * 2020-06-25 11:21
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    @Id
    /* 详情id */
    private String detailId;

    /* 订单id */
    private String orderId;

    /* 商品id */
    private String productId;

    /* 商品名称 */
    private String productName;

    /* 单价 */
    private BigDecimal productPrice;

    /* 数量 */
    private Integer productQuantity;

    /* 商品小图 */
    private String productIcon;
}
