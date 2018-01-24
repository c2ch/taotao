package com.c2c.portal.service;

import com.c2c.portal.pojo.SearchResult;

/**
 * @ClassName: SearchService
 * @Description:搜索服务的Service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 17:02
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface SearchService {

    /**
     * 搜索服务
     * @param keyword
     * @param page
     * @param rows
     * @return
     */
    SearchResult search(String keyword,int page,int rows);
}
