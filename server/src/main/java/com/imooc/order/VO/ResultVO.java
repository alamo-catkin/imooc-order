package com.imooc.order.VO;

import lombok.Data;

/**
 * Created by Catkin
 * 2020-06-25 13:43
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
