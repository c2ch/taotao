package com.c2c.service.impl;

import com.c2c.result.EasyUIDataGridResult;
import com.c2c.mapper.TbItemMapper;
import com.c2c.pojo.TbItem;
import com.c2c.pojo.TbItemExample;
import com.c2c.pojo.TbItemExample.Criteria;
import com.c2c.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright: Copyright (c) 2018 cc
 *
 * @ClassName: ItemServiceImpl
 * @Description: 商品服务接口的实现类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/4 10:22
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    /**
     * 根据id获取商品信息
     * @param itemId
     * @return
     */
    @Override
    public TbItem getItemById(long itemId) {

//        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);

        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = tbItemMapper.selectByExample(example);
        if(!list.isEmpty()){
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    /**
     * 利用分页插件， 分页返回商品信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGridResult getItemList(Integer currentPage, Integer pageSize) {
        TbItemExample example = new TbItemExample();
        //设置分页
        PageHelper.startPage(currentPage,pageSize);
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);

        //得到分页详细信息，如总数等等
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(tbItems);
        long total = pageInfo.getTotal();
        EasyUIDataGridResult result = new EasyUIDataGridResult(total,tbItems);

        return result;
    }

}
