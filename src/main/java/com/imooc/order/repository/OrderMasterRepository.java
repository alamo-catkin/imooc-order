package com.imooc.order.repository;

import com.imooc.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Catkin
 * 2020-06-25 11:26
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

}
