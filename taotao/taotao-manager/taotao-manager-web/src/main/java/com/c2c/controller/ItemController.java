package com.c2c.controller;

import com.c2c.pojo.SearchItem;
import com.c2c.pojo.TbItem;
import com.c2c.result.EasyUIDataGridResult;
import com.c2c.result.TaotaoResult;
import com.c2c.service.ItemCatService;
import com.c2c.service.ItemService;
import com.c2c.util.HttpClientUtil;
import com.c2c.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

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

    @Autowired
    private ItemCatService itemCatService;

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Value("${SYNC_URL}")
    private String SYNC_URL;

    @Value("${DELETE_URL}")
    private String DELETE_URL;

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
        TaotaoResult result = itemService.saveItem(tbItem, desc, itemParams);

        //新增商品，需要调用服务同步索引库
        try {
            //构建SearchItem对象
            SearchItem searchItem = new SearchItem();
            searchItem.setId(tbItem.getId()+"");
            searchItem.setTitle(tbItem.getTitle());
            searchItem.setItemDesc(desc);
            searchItem.setImage(tbItem.getImage());
            searchItem.setSellPoint(tbItem.getSellPoint());
            searchItem.setCategoryName(itemCatService.getItemCat(tbItem.getCid()).getName());
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("param", JsonUtils.objectToJson(searchItem));
            HttpClientUtil.doGet(SEARCH_BASE_URL + SYNC_URL, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据商品ID展示规格参数信息
     * @param ItemId
     * @return
     */
    @RequestMapping("/param/{ItemId}")
    public String getItemParamHtml(@PathVariable("ItemId") long ItemId, Model model){

        String html = itemService.getItemParamHtml(ItemId);
        model.addAttribute("model", html);
        return "show-param";
    }

    /**
     * 根据商品id删除商品
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteItemById(String ids) {
        TaotaoResult result = itemService.deleteItemById(ids);
        //删除商品也需要删除索引
        try {
            //判断是否是多个id
            if(ids.indexOf(",")>0){
                String[] id = ids.split(",");
                for (int i = 0; i < id.length; i++) {
                    HttpClientUtil.doGet(SEARCH_BASE_URL + DELETE_URL + id[i]);
                }
            }else{
                HttpClientUtil.doGet(SEARCH_BASE_URL+DELETE_URL+ids);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
