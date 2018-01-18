package com.c2c.service;

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

    List<EasyUITreeNodeResult> getTreeNode(long parentId);
}
