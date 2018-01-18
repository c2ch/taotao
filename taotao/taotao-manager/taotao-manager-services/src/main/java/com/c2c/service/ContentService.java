package com.c2c.service;

import com.c2c.pojo.TbContent;
import com.c2c.result.EasyUIDataGridResult;
import com.c2c.result.TaotaoResult;

/**
 * @ClassName: ContentService
 * @Description: 内容的service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 23:39
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface ContentService {

    /**
     * 根据内容分类id分页查询内容信息
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGridResult getContentList(long categoryId, Integer page, Integer rows);

    /**
     * 新增内容
     * @param content
     * @return
     */
    TaotaoResult insertContent(TbContent content);
}
