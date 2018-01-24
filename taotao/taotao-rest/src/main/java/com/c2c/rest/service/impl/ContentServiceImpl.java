package com.c2c.rest.service.impl;

import com.c2c.mapper.TbContentMapper;
import com.c2c.pojo.TbContent;
import com.c2c.pojo.TbContentExample;
import com.c2c.rest.component.JedisClient;
import com.c2c.rest.service.ContentService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ContentServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/18 10:39
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    /**
     * 根据分类id查询内容列表
     * @param cid
     * @return
     */
    @Override
    public List<TbContent> getContentList(long cid) {

        //每次查询之前应该先从Redis缓存中查询有没有，没有再查询数据库
        try {
            String jsonVal = jedisClient.hget(CONTENT_KEY, cid + "");
            if(StringUtils.isNotBlank(jsonVal)){
                //如果不为空，就把json数据转成java对象直接返回
                List<TbContent> contentList = JsonUtils.jsonToList(jsonVal, TbContent.class);
                return contentList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //设置查询条件，查询列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);

        //查询完数据库往Redis缓存中添加，如果添加失败也不能影响正常的业务流程，所以要放在try里
        try {
            //为了规范key可以使用HashSet，
            //定义一个保存内容的Key，hash中的每一项就是cid，value就是list转成json数据
            jedisClient.hset(CONTENT_KEY,cid+"", JsonUtils.objectToJson(list));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 根据cid同步内容列表缓存
     * @param cid
     * @return
     */
    @Override
    public TaotaoResult syncContentList(long cid) {
        //同步缓存其实就是把缓存清空,让其下次查询再加入到缓存中
        jedisClient.hdel(CONTENT_KEY, cid + "");
        return TaotaoResult.ok();
    }
}
