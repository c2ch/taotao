package com.c2c.rest.service.impl;

import com.c2c.mapper.TbItemCatMapper;
import com.c2c.pojo.TbItemCat;
import com.c2c.pojo.TbItemCatExample;
import com.c2c.rest.pojo.ItemCatNode;
import com.c2c.rest.result.ItemCatResult;
import com.c2c.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ItemCatServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 14:58
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    /**
     * 查询商品分类集合
     * @return
     */
    @Override
    public ItemCatResult getItemCatList() {
        ItemCatResult result = new ItemCatResult();
        result.setData(getItemCat(0l));
        return result;
    }

    /**
     * 递归完成商品分类的组装
     */

    public List getItemCat(long parentId){

        TbItemCatExample example = new TbItemCatExample();
        //查询父节点数据
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        List resultList = new ArrayList();
        //由于首页只展示14行数据，所以要搞个计数器控制下
        int count = 0;
        for (TbItemCat tbItemCat : list) {
            if(count>=14){
                break;
            }
            //查询当前节点的id，然后再查询该节点的子节点
            long id = tbItemCat.getId();
            //如果是父节点
            if(tbItemCat.getIsParent()){
                ItemCatNode node = new ItemCatNode();
                node.setUrl("/products/"+tbItemCat.getId()+".html");
                if(tbItemCat.getParentId()==0){
                    node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                    //第一级节点不要超过14个，否则页面展示就多余了。
                    count++;
                }else{
                    node.setName(tbItemCat.getName());
                }
                node.setItems(getItemCat(tbItemCat.getId()));
                //把node添加到结果集list中
                resultList.add(node);

            }else{
                //是叶子节点
                String item = "/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName();
                resultList.add(item);
            }
        }

        return resultList;
    }
}
