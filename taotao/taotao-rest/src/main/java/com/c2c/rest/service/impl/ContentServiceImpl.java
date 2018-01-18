package com.c2c.rest.service.impl;

import com.c2c.mapper.TbContentMapper;
import com.c2c.pojo.TbContent;
import com.c2c.pojo.TbContentExample;
import com.c2c.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 根据分类id查询内容列表
     * @param categoryId
     * @return
     */
    @Override
    public List<TbContent> getContentList(long categoryId) {

        //设置查询条件，查询列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);

        return list;
    }
}
