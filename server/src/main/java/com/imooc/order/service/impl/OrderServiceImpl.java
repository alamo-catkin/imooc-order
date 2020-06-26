package com.imooc.order.service.impl;

import com.imooc.order.dataobject.OrderDetail;
import com.imooc.order.dataobject.OrderMaster;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.enums.OrderStatusEnum;
import com.imooc.order.enums.PayStatusEnum;
import com.imooc.order.repository.OrderDetailRepository;
import com.imooc.order.repository.OrderMasterRepository;
import com.imooc.order.service.OrderService;
import com.imooc.order.utils.KeyUtil;
import com.imooc.product.client.ProductClient;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Catkin
 * 2020-06-25 12:36
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        //1. 查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(
                OrderDetail::getProductId
        ).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listProduct(productIdList);

        //2. 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfoOutput : productInfoList) {
                if (orderDetail.getProductId().equals(productInfoOutput.getProductId())) {
                    // 单价*数量
                    orderAmount = productInfoOutput.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);

                    BeanUtils.copyProperties(productInfoOutput, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //3. 扣库存(调用商品服务)
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream().map(
                orderDetail -> new DecreaseStockInput(orderDetail.getProductId(), orderDetail.getProductQuantity())
        ).collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

        // 4. 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
