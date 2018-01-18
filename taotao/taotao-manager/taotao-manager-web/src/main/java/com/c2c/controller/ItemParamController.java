package com.c2c.controller;

import com.c2c.result.EasyUIDataGridResult;
import com.c2c.result.TaotaoResult;
import com.c2c.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *
 * @ClassName: ItemParamController
 * @Description: 商品规格参数模板的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/11 17:00
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据商品类目id查询商品模板参数
     * @param cid
     * @return
     */
    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable("cid") long cid){
        TaotaoResult result = itemParamService.getItemParamByCid(cid);
        return result;
    }


    /**
     * 分页展示商品规格参数模板
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(@RequestParam(value="page",defaultValue="1") Integer currentPage, @RequestParam(value="rows",defaultValue = "50")Integer pageSize){
        EasyUIDataGridResult result = itemParamService.getItemParamList(currentPage, pageSize);
        return  result;
    }


    /**
     * 根据商品类目id保存规格参数模板
     *
     * @param cid
     * @param paramData 规格信息
     * @return
     */
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult saveItemParam(@PathVariable("cid") long cid, String paramData) {
        TaotaoResult result = itemParamService.saveItemParam(cid, paramData);
        return result;
    }

    /**
     * 根据规格参数模板ID删除规格参数模板
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteItemParamById(String ids){
        TaotaoResult result = itemParamService.deleteItemParamById(ids);
        return result;
    }
}
