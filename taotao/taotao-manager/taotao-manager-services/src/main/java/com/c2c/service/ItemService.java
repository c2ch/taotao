package com.c2c.service;

import com.c2c.result.EasyUIDataGridResult;
import com.c2c.pojo.TbItem;
import com.c2c.result.TaotaoResult;

/**
 *  cc
 *
 * @ClassName: ItemService
 * @Description: 商品服务接口
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/4 10:20
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface ItemService {

    /**
     * 根据id获取商品信息
     * @param itemId
     * @return
     */
    TbItem getItemById(long itemId);

    /**
     * 分页返回easyUI格式的商品信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    EasyUIDataGridResult getItemList(Integer currentPage, Integer pageSize);

    /**
     * 添加商品信息
     * @param tbItem
     * @param desc 商品描述，不在TbItem类中，在TbItemDesc中
     * @param itemParams 商品规格参数
     * @return
     */
    TaotaoResult saveItem(TbItem tbItem,String desc,String itemParams);

    /**
     * 根据商品的id删除商品
     * @param ids
     * @return
     */
    TaotaoResult deleteItemById(String ids);

    /**
     * 根据商品ID展示规格参数信息
     * @param ItemId
     * @return
     */
    String getItemParamHtml(long ItemId);


}
