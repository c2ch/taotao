package com.c2c.portal.controller;

import com.c2c.pojo.TbItem;
import com.c2c.pojo.TbItemDesc;
import com.c2c.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ItemController
 * @Description: 商品信息的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/26 16:37
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
     * 根据商品ID查询商品
     * @param itemId
     * @return
     */
    @RequestMapping("/{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model) {
        TbItem item = itemService.getItemById(itemId);
        model.addAttribute("item", item);
        return "item";
    }

    /**
     * 根据商品id查询商品详情
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/desc/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String showItemDesc(@PathVariable Long itemId){
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        String desc = itemDesc.getItemDesc();
        return desc;
    }

    /**
     * 根据商品id查询商品规格参数
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/param/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String showParamItem(@PathVariable Long itemId){
        String param = itemService.getItemParamById(itemId);
        return param;
    }
}
