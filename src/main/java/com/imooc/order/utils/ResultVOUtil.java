package com.imooc.order.utils;

import com.imooc.order.VO.ResultVO;

/**
 * Created by Catkin
 * 2020-06-25 13:44
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
