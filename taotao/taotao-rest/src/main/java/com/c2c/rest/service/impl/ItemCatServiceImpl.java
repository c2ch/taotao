package com.c2c.rest.service.impl;

import com.c2c.mapper.TbItemCatMapper;
import com.c2c.pojo.TbItemCat;
import com.c2c.pojo.TbItemCatExample;
import com.c2c.rest.component.JedisClient;
import com.c2c.rest.pojo.ItemCatNode;
import com.c2c.rest.result.ItemCatResult;
import com.c2c.rest.service.ItemCatService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_CATEGORY_KEY}")
    private String CONTENT_CATEGORY_KEY;

    /**
     * 查询商品分类集合
     * @return
     */
    @Override
    public ItemCatResult getItemCatList() {

        //先查询缓存中是否存在
        try {
            String jsonVal = jedisClient.get(CONTENT_CATEGORY_KEY);
            if(StringUtils.isNotBlank(jsonVal)){
                //如果不为空就从缓存中取数据
                ItemCatResult catResult = JsonUtils.jsonToPojo(jsonVal, ItemCatResult.class);
                return catResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ItemCatResult result = new ItemCatResult();
        result.setData(getItemCat(0l));

        //查询完成后放入到缓存中
        try {
            jedisClient.set(CONTENT_CATEGORY_KEY, JsonUtils.objectToJson(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 缓存同步
     * @return
     */
    @Override
    public TaotaoResult syncItemCatList() {
        jedisClient.del(CONTENT_CATEGORY_KEY);
        return TaotaoResult.ok();
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
