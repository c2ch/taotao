package com.c2c.order.controller;

import com.c2c.order.pojo.OrderInfo;
import com.c2c.order.service.OrderService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: OrderController
 * @Description: 订单的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/2/1 10:14
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody OrderInfo orderInfo) {
        try {
            TaotaoResult result = orderService.createOrder(orderInfo);
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
