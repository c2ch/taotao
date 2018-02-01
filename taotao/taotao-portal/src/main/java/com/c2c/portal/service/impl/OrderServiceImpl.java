package com.c2c.portal.service.impl;

import com.c2c.portal.pojo.OrderInfo;
import com.c2c.portal.service.OrderService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.HttpClientUtil;
import com.c2c.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName: OrderServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/2/1 10:24
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    @Override
    public String createOrder(OrderInfo orderInfo) {
        //把OrderInfo转换成json
        String json = JsonUtils.objectToJson(orderInfo);
        //提交订单数据
        String jsonResult = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, json);
        //转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.format(jsonResult);
        //取订单号
        String orderId = taotaoResult.getData().toString();
        return orderId;
    }
}
