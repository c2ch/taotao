package com.c2c.rest.service.impl;

import com.c2c.mapper.TbItemDescMapper;
import com.c2c.mapper.TbItemMapper;
import com.c2c.mapper.TbItemParamItemMapper;
import com.c2c.pojo.TbItem;
import com.c2c.pojo.TbItemDesc;
import com.c2c.pojo.TbItemParamItem;
import com.c2c.pojo.TbItemParamItemExample;
import com.c2c.rest.component.JedisClient;
import com.c2c.rest.service.ItemService;
import com.c2c.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ItemServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/26 14:36
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper descMapper;

    @Autowired
    private TbItemParamItemMapper paramItemMapper;

    @Autowired
    private JedisClient jedisClient;
    @Value("${ITEM_KEY}")
    private String ITEM_KEY;
    @Value("${ITEM_BASE_INFO_KEY}")
    private String ITEM_BASE_INFO_KEY;
    @Value("${ITEM_DESC_KEY}")
    private String ITEM_DESC_KEY;
    @Value("${ITEM_PARAM_KEY}")
    private String ITEM_PARAM_KEY;
    @Value("${ITEM_EXPIRE_SECOND}")
    private Integer ITEM_EXPIRE_SECOND;


    /**
     * 根据商品ID获取商品信息
     * @param itemId
     * @return
     */
    @Override
    public TbItem getItemById(Long itemId) {

        try {
            //从Redis缓存中取商品信息
            String jsonVal = jedisClient.get(ITEM_KEY + ":" + itemId +
                    ":" + ITEM_BASE_INFO_KEY);
            //判断数据是否存在
            if (StringUtils.isNotBlank(jsonVal)) {
                //把json数据转成pojo返回
                TbItem item = JsonUtils.jsonToPojo(jsonVal, TbItem.class);
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据商品ID查询商品基本信息
        TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);

        //向Redis中添加缓存
        //添加缓存的原则是不影响正常逻辑
        try {
            //将商品转换成json串
            String itemJson = JsonUtils.objectToJson(tbItem);
            //向Redis中添加缓存
            jedisClient.set(ITEM_KEY+":"+itemId+":"+ITEM_BASE_INFO_KEY, itemJson);
            //设置key的过期时间
            jedisClient.expire(ITEM_KEY+":"+itemId+":"+ITEM_BASE_INFO_KEY,ITEM_EXPIRE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbItem;
    }

    /**
     * 根据商品id查询商品详情
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc getItemDescById(Long itemId) {

        try {
            //先查下缓存中
            String jsonVal = jedisClient.get(ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY);
            //如果有数据
            if(StringUtils.isNotBlank(jsonVal)){
                return JsonUtils.jsonToPojo(jsonVal, TbItemDesc.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询商品详情
        TbItemDesc itemDesc = descMapper.selectByPrimaryKey(itemId);

        try {
            //添加到缓存中
            String descJson = JsonUtils.objectToJson(itemDesc);
            jedisClient.set(ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY, descJson);
            //设置key的过期时间
            jedisClient.expire(ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY,
                    ITEM_EXPIRE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemDesc;
    }

    /**
     * 根据商品id查询商品规格参数
     * @param itemId
     * @return
     */
    @Override
    public TbItemParamItem getItemParamById(Long itemId) {
        try {
            //先查下缓存中
            String jsonVal = jedisClient.get(ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY);
            //如果有数据
            if(StringUtils.isNotBlank(jsonVal)){
                return JsonUtils.jsonToPojo(jsonVal, TbItemParamItem.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 根据商品id查询规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = paramItemMapper.selectByExampleWithBLOBs(example);
        if(list.isEmpty()){
            return null;
        }
        TbItemParamItem paramItem = list.get(0);

        try {
            //添加到缓存中
            String paramItemJson = JsonUtils.objectToJson(paramItem);
            jedisClient.set(ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY, paramItemJson);
            //设置key的过期时间
            jedisClient.expire(ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY,
                    ITEM_EXPIRE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paramItem;
    }
}
