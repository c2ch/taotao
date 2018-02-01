package com.c2c.order.service;

import com.c2c.order.pojo.OrderInfo;
import com.c2c.result.TaotaoResult;

/**
 * @ClassName: OrderService
 * @Description: 订单的Service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/2/1 9:52
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
    TaotaoResult createOrder(OrderInfo orderInfo);
}
