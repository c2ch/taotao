package com.c2c.portal.controller;

import com.c2c.portal.pojo.CartItem;
import com.c2c.portal.service.CartService;
import com.c2c.result.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: CartController
 * @Description: 购物车的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/31 16:07
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加购物车中的商品到cookie
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cart/add/{itemId}")
    public String addCartToCookie(@PathVariable Long itemId, Integer num,
          HttpServletRequest request, HttpServletResponse response){
        cartService.addCartToCookie(itemId, num, request, response);
        return "cartSuccess";
    }

    /**
     * 从Cookie中取购物车列表展示
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/cart/cart")
    public String showCartFromCookie(HttpServletRequest request,
                                     Model model) {
        List<CartItem> list = cartService.showCartFromCookie(request);
        model.addAttribute("cartList", list);
        return "cart";
    }

    /**
     * 修改购物车数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public TaotaoResult updateCartNum(@PathVariable Long itemId,@PathVariable Integer num,
              HttpServletRequest request,HttpServletResponse response){
        TaotaoResult result = cartService.updateCartNum(itemId, num, request, response);
        return result;
    }

    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId,
        HttpServletRequest request,HttpServletResponse response){
        cartService.deleteCartItem(itemId, request, response);
        return "redirect:/cart/cart.html";
    }
}
