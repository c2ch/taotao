package com.c2c.service;

import com.c2c.result.EasyUITreeNodeResult;
import com.c2c.result.TaotaoResult;

import java.util.List;

/**
 * @ClassName: ContentCategoryService
 * @Description:内容分类管理的service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 16:12
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface ContentCategoryService {

    /**
     * 查询内容分类管理的列表展示（属性结构）
     * @param patenId
     * @return
     */
    List<EasyUITreeNodeResult> getContentCategoryList(long patenId);

    /**
     * 新建分类
     * @param parentId
     * @param name
     * @return
     */
    TaotaoResult createCategory(long parentId,String name);

    /**
     * 删除分类
     * @param id
     */
    void deleteCategory(long id);


    /**
     * 重命名分类
     * @param id
     * @param name
     */
    void renameCategory(long id, String name);
}
