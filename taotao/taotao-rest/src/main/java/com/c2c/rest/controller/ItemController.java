package com.c2c.rest.controller;

import com.c2c.pojo.TbItem;
import com.c2c.pojo.TbItemDesc;
import com.c2c.pojo.TbItemParamItem;
import com.c2c.rest.service.ItemService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ItemController
 * @Description: 商品信息的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/26 14:57
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 根据商品id获取商品基本信息
     * @param itemId
     * @return
     */
    @RequestMapping("/base/{itemId}")
    @ResponseBody
    public TaotaoResult getItem(@PathVariable long itemId) {
        try {
            TbItem tbItem = itemService.getItemById(itemId);
            return TaotaoResult.ok(tbItem);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 根据商品id查询商品详情
     * @param itemId
     * @return
     */
    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public TaotaoResult getItemDesc(@PathVariable long itemId) {
        try {
            TbItemDesc tbItem = itemService.getItemDescById(itemId);
            return TaotaoResult.ok(tbItem);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 根据商品id查询商品规格参数
     * @param itemId
     * @return
     */
    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public TaotaoResult getItemParam(@PathVariable long itemId) {
        try {
            TbItemParamItem tbItem = itemService.getItemParamById(itemId);
            return TaotaoResult.ok(tbItem);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
