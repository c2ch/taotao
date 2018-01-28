package com.c2c.portal.service;

import com.c2c.pojo.TbItem;
import com.c2c.pojo.TbItemDesc;

/**
 * @ClassName: ItemService
 * @Description: 商品信息的Service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/26 14:34
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface ItemService {

    /**
     * 根据商品ID查询商品
     * @param itemId
     * @return
     */
    TbItem getItemById(Long itemId);

    /**
     * 根据商品id查询商品详情
     * @param itemId
     * @return
     */
    TbItemDesc getItemDescById(Long itemId);

    /**
     * 根据商品id查询商品规格参数
     * @param itemId
     * @return
     */
    String getItemParamById(Long itemId);
}

