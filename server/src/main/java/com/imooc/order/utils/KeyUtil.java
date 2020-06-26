package com.imooc.order.utils;

import java.util.Random;

/**
 * Created by Catkin
 * 2020-06-25 12:57
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间戳+随机数
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
