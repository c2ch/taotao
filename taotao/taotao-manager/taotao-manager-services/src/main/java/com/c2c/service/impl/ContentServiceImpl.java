package com.c2c.service.impl;

import com.c2c.mapper.TbContentMapper;
import com.c2c.pojo.TbContent;
import com.c2c.pojo.TbContentExample;
import com.c2c.result.EasyUIDataGridResult;
import com.c2c.result.TaotaoResult;
import com.c2c.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ContentServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 23:42
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
     * 根据内容分类id分页查询内容信息
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getContentList(long categoryId, Integer page, Integer rows) {
        EasyUIDataGridResult result = new EasyUIDataGridResult();

        //根据分类id分页查询内容
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        //设置分页信息
        PageHelper.startPage(page,rows);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(example);

        //查询分页详细信息
        PageInfo pageInfo = new PageInfo(tbContents);
        result.setTotal(pageInfo.getTotal());
        result.setRows(tbContents);

        return result;
    }

    /**
     * 新增内容
     * @param content
     * @return
     */
    @Override
    public TaotaoResult insertContent(TbContent content) {
        Date date = new Date();

        content.setCreated(date);
        content.setUpdated(date);
        //保存数据
        tbContentMapper.insert(content);

        return TaotaoResult.ok();
    }
}
