package com.c2c.search.service;

import com.c2c.result.TaotaoResult;

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
}
