package com.c2c.portal.service.impl;

import com.c2c.pojo.TbItem;
import com.c2c.portal.pojo.CartItem;
import com.c2c.portal.service.CartService;
import com.c2c.portal.service.ItemService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.CookieUtils;
import com.c2c.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CartServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/31 15:43
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ItemService itemService;

    @Value("${COOKIE_CART_NAME}")
    private String COOKIE_CART_NAME;
    @Value("${COOKIE_EXPIRE}")
    private Integer COOKIE_EXPIRE;

    /**
     * 添加购物车中的商品到cookie
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @Override
    public TaotaoResult addCartToCookie(Long itemId, Integer num,
            HttpServletRequest request, HttpServletResponse response) {
        // 1、接收商品id
        // 2、从cookie中购物车商品列表
        List<CartItem> cartList = getCartItemList(request);
        // 3、从购物车列表中查询列表是否存在此商品
        boolean isIn = false;
        for (CartItem cartItem : cartList) {
            // 4、如果存在商品的数量加上参数中的商品数量
            if (cartItem.getId().longValue() == itemId) {
                cartItem.setNum(num+cartItem.getNum());
                isIn = true;
                break;
            }
        }
        // 5、如果不存在，调用rest服务，根据商品id获得商品数据。
        if (!isIn) {
            TbItem tbItem = itemService.getItemById(itemId);
            // 6、把商品数据添加到列表中
            CartItem cart = new CartItem();
            cart.setNum(num);
            cart.setPrice(tbItem.getPrice());
            cart.setTitle(tbItem.getTitle());
            cart.setId(itemId);
            if (StringUtils.isNotBlank(tbItem.getImage())) {
                String image = tbItem.getImage();
                String[] strings = image.split(",");
                cart.setImage(strings[0]);
            }
            //添加到购物车列表中去
            cartList.add(cart);
        }
        // 7、把购物车商品列表写入cookie,需要编码
        CookieUtils.setCookie(request, response, COOKIE_CART_NAME,
                JsonUtils.objectToJson(cartList),COOKIE_EXPIRE,true);
        // 8、返回TaotaoResult
        return TaotaoResult.ok();
    }


    /**
     * 获取cookie中的购物车商品列表
     * @param request
     * @return
     */
    private List<CartItem> getCartItemList(HttpServletRequest request) {
        try {
            //获取cookie中的购物车商品
            String value = CookieUtils.getCookieValue(request, COOKIE_CART_NAME,true);
            //转换成java对象
            List<CartItem> itemList = JsonUtils.jsonToList(value, CartItem.class);

            return itemList == null ? new ArrayList<CartItem>() : itemList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<CartItem>();
        }
    }

    /**
     * 从cookie中取购物车列表展示
     * @param request
     * @return
     */
    @Override
    public List<CartItem> showCartFromCookie(HttpServletRequest request) {
        List<CartItem> list = getCartItemList(request);
        return list;
    }

    /**
     * 修改购物车数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @Override
    public TaotaoResult updateCartNum(Long itemId, Integer num,
          HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取出购物车列表
        List<CartItem> list = getCartItemList(request);

        //根据商品id修改商品数量
        for (CartItem cartItem : list) {
            if(cartItem.getId().longValue() == itemId){
                cartItem.setNum(num);
                break;
            }
        }

        //写入cookie
        CookieUtils.setCookie(request, response, COOKIE_CART_NAME,
                JsonUtils.objectToJson(list),COOKIE_EXPIRE,true);
        return TaotaoResult.ok();
    }

    /**
     * 删除购物车商品
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @Override
    public TaotaoResult deleteCartItem(Long itemId,
             HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取出购物车列表
        List<CartItem> list = getCartItemList(request);

        //根据商品id删除商品
        for (CartItem cartItem : list) {
            if(cartItem.getId().longValue() == itemId){
                list.remove(cartItem);
                break;
            }
        }

        //写入cookie
        CookieUtils.setCookie(request, response, COOKIE_CART_NAME,
                JsonUtils.objectToJson(list),COOKIE_EXPIRE,true);
        return TaotaoResult.ok();
    }
}
