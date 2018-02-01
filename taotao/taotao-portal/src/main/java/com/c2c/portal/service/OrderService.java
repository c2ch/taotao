package com.c2c.portal.service;

import com.c2c.portal.pojo.OrderInfo;

/**
 * @ClassName: OrderService
 * @Description: 订单的Service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/2/1 10:22
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    String createOrder(OrderInfo orderInfo);
}
