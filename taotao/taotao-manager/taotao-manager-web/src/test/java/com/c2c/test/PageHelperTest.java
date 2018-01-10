package com.c2c.test;

import com.c2c.base.SpringJunitBase;
import com.c2c.mapper.TbItemMapper;
import com.c2c.pojo.TbItem;
import com.c2c.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Copyright: Copyright (c) 2018 cc
 *
 * @ClassName: PageHelperTest
 * @Description: 分页插件的测试
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/4 15:31
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class PageHelperTest extends SpringJunitBase{

    @Autowired
    private TbItemMapper tbItemMapper;


    @Test
    public void testPageHelper(){
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(1, 10);
        List<TbItem> list = tbItemMapper.selectByExample(example);
        for (TbItem tbItem : list) {
            System.out.println(tbItem.getTitle());
        }
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        System.out.println("总数是:"+pageInfo.getTotal());
    }
}
