package com.c2c.rest.service;

import com.c2c.rest.result.ItemCatResult;

/**
 * @ClassName: ItemCatService
 * @Description: 商品分类的service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 14:57
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface ItemCatService {

    /**
     * 查询商品分类集合
     * @return
     */
    ItemCatResult getItemCatList();
}
