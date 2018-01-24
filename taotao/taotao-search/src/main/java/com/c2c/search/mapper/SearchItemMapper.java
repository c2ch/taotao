package com.c2c.search.mapper;

import com.c2c.search.pojo.SearchItem;

import java.util.List;

/**
 * @ClassName: SearchItemMapper
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 11:59
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface SearchItemMapper {

    List<SearchItem> getItemList();
}
