package com.c2c.controller;

import com.c2c.result.EasyUIDataGridResult;
import com.c2c.pojo.TbItem;
import com.c2c.result.TaotaoResult;
import com.c2c.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  cc
 *
 * @ClassName: ItemController
 * @Description: 商品的控制器
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/4 10:28
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
     * 根据ID获取商品
     */
    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable(value = "itemId") Long id){
       TbItem tbItem = itemService.getItemById(id);
        return tbItem;
    }

    /**
     * 分页展示商品列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(@RequestParam(value="page",defaultValue="1") Integer currentPage, @RequestParam(value="rows",defaultValue = "50")Integer pageSize){
        //查询商品列表
        EasyUIDataGridResult result = itemService.getItemList(currentPage, pageSize);
        return result;
    }

    /**
     * 保存商品信息
     * @param tbItem
     * @param desc
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult saveItem(TbItem tbItem,String desc,String itemParams){
      return  itemService.saveItem(tbItem, desc,itemParams);
    }


    @RequestMapping("/param/{ItemId}")
    public String getItemParamHtml(@PathVariable("ItemId") long ItemId, Model model){

        String html = itemService.getItemParamHtml(ItemId);
        model.addAttribute("model", html);
        return "show-param";
    }

}
