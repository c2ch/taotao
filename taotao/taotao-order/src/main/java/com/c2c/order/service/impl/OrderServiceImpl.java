package com.c2c.order.service.impl;

import com.c2c.mapper.TbOrderItemMapper;
import com.c2c.mapper.TbOrderMapper;
import com.c2c.mapper.TbOrderShippingMapper;
import com.c2c.order.component.JedisClient;
import com.c2c.order.pojo.OrderInfo;
import com.c2c.order.service.OrderService;
import com.c2c.pojo.TbOrderItem;
import com.c2c.pojo.TbOrderShipping;
import com.c2c.result.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: OrderServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/2/1 9:53
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper shippingMapper;

    @Value("${ORDER_NO_GEN_KEY}")
    private String ORDER_NO_GEN_KEY;
    @Value("${ORDER_NO_BEGIN}")
    private String ORDER_NO_BEGIN;
    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;


    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    @Override
    public TaotaoResult createOrder(OrderInfo orderInfo) {

        // 一、插入订单表
        // 1、接收数据OrderInfo
        // 2、生成订单号
        //取订单号
        String id = jedisClient.get(ORDER_NO_GEN_KEY);
        if (StringUtils.isBlank(id)) {
            //如果订单号生成key不存在设置初始值
            jedisClient.set(ORDER_NO_GEN_KEY, ORDER_NO_BEGIN);
        }
        //订单号自增
        Long orderId = jedisClient.incr(ORDER_NO_GEN_KEY);
        // 3、补全字段
        orderInfo.setOrderId(orderId.toString());
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        Date date = new Date();
        orderInfo.setCreateTime(date);
        orderInfo.setUpdateTime(date);
        // 4、插入订单表
        orderMapper.insert(orderInfo);
        // 二、插入订单明细
        // 1、补全字段
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem orderItem : orderItems) {
            // 2、生成订单明细id，使用redis的incr命令生成。
            Long detailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            orderItem.setId(detailId.toString());
            //订单号
            orderItem.setOrderId(orderId.toString());
            // 3、插入数据
            orderItemMapper.insert(orderItem);
        }
        // 三、插入物流表
        // 1、补全字段
        TbOrderShipping shipping = orderInfo.getOrderShipping();
        // 2、插入数据
        shipping.setOrderId(orderId.toString());
        shipping.setCreated(date);
        shipping.setUpdated(date);
        // 返回TaotaoResult包装订单号。
        return TaotaoResult.ok(orderId);
    }
}
