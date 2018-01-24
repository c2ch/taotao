package com.c2c.test;

import com.c2c.base.SpringJunitBase;
import com.c2c.search.mapper.SearchItemMapper;
import com.c2c.search.pojo.SearchItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: TestSelect
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 14:27
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class TestSelect extends SpringJunitBase {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Test
    public void getItemList() {
        List<SearchItem> list = searchItemMapper.getItemList();
        for (SearchItem searchItem : list) {
            System.out.println(searchItem.getTitle());
        }
    }
}
