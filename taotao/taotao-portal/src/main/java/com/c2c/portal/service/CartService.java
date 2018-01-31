package com.c2c.portal.service;

import com.c2c.portal.pojo.CartItem;
import com.c2c.result.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: CartService
 * @Description: 购物车的Service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/31 15:40
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface CartService {

    /**
     * 添加购物车的商品到cookie中
     * @param itemId
     * @param num
     * @return
     */
    TaotaoResult addCartToCookie(Long itemId, Integer num,
           HttpServletRequest request, HttpServletResponse response);

    /**
     * 从cookie中取购物车列表展示
     * @param request
     * @return
     */
    List<CartItem> showCartFromCookie(HttpServletRequest request);

    /**
     * 修改购物车数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    TaotaoResult updateCartNum(Long itemId, Integer num,
           HttpServletRequest request, HttpServletResponse response);


    /**
     * 删除购物车商品
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    TaotaoResult deleteCartItem(Long itemId,
       HttpServletRequest request, HttpServletResponse response);

}
