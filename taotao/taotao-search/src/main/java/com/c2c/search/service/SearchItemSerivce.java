package com.c2c.search.service;

import com.c2c.result.TaotaoResult;
import com.c2c.search.pojo.SearchItem;

/**
 * @ClassName: SearchItemSerivce
 * @Description: 搜索的服务
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 14:21
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface SearchItemSerivce {

    /**
     * 导入索引库
     * @return
     */
   TaotaoResult importItems() throws Exception;

    /**
     * 查询
      * @param queryString
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
   TaotaoResult search(String queryString,int page,int rows) throws Exception;


    /**
     * 后台修改商品信息后同步索引库服务
     * @param item
     * @return
     * @throws Exception
     */
    TaotaoResult syncIndex(SearchItem item) throws Exception;

    /**
     * 后台删除商品信息后删除索引服务
     * @param id
     * @return
     * @throws Exception
     */
    TaotaoResult deleteIndex(String id) throws  Exception;
}
