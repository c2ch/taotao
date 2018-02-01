package com.c2c.order.pojo;

import com.c2c.pojo.TbOrder;
import com.c2c.pojo.TbOrderItem;
import com.c2c.pojo.TbOrderShipping;

import java.util.List;

/**
 * @ClassName: OrderInfo
 * @Description: 订单的实体类，封装了订单明细和物流详情
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/2/1 9:48
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class OrderInfo extends TbOrder {

    //一个订单对应多个订单详情，一个物流详情
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;


    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
