package com.c2c.service;

import com.c2c.pojo.TbItemCat;
import com.c2c.result.EasyUITreeNodeResult;

import java.util.List;

/**
 *  cc
 *
 * @ClassName: ItemCatService
 * @Description: 商品类目树形的Service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/5 17:12
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface ItemCatService {

    /**
     * 根据父Id查询商品子类目
     * @param parentId
     * @return
     */
    List<EasyUITreeNodeResult> getTreeNode(long parentId);

    /**
     * 根据id查询商品子类目
     * @param id
     * @return
     */
    TbItemCat getItemCat(long id);
}
