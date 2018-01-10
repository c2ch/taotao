package com.c2c.service;

import com.c2c.result.EasyUIDataGridResult;
import com.c2c.pojo.TbItem;

/**
 * Copyright: Copyright (c) 2018 cc
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

}
