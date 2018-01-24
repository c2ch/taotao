package com.c2c.rest.service;

import com.c2c.pojo.TbContent;
import com.c2c.result.TaotaoResult;

import java.util.List;

/**
 * @ClassName: ContentService
 * @Description: content的Service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/18 10:36
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface ContentService {

    /**
     * 根据分类id查询内容列表
     * @param categoryId
     * @return
     */
    List<TbContent> getContentList(long categoryId);

    /**
     * 根据cid同步内容列表缓存
     * @param cid
     * @return
     */
    TaotaoResult syncContentList(long cid);
}
