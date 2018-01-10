package com.c2c.service.impl;

import com.c2c.result.EasyUITreeNodeResult;
import com.c2c.mapper.TbItemCatMapper;
import com.c2c.pojo.TbItemCat;
import com.c2c.pojo.TbItemCatExample;
import com.c2c.pojo.TbItemCatExample.Criteria;
import com.c2c.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2018 cc
 *
 * @ClassName: ItemCatServiceImpl
 * @Description: 商品类目的服务实现类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/5 17:13
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {


    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EasyUITreeNodeResult> getTreeNode(long parentId) {

        //创建查询
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(example);

        //创建结果集合
        List<EasyUITreeNodeResult> resultList = new ArrayList<EasyUITreeNodeResult>();

        //遍历查询到的商品类目,并设置到到返回结果集中
        for(TbItemCat cat : tbItemCats){
            EasyUITreeNodeResult node = new EasyUITreeNodeResult();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent()?"closed":"open");
            resultList.add(node);
        }

        return resultList;
    }
}
