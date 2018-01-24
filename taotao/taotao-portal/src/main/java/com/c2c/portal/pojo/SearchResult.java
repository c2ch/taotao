package com.c2c.portal.pojo;

import java.util.List;

/**
 * @ClassName: SearchResult
 * @Description:搜索查询的返回结果对象
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 15:27
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class SearchResult {

    private List<SearchItem> itemList;
    private Long recordCount;
    private Integer pageCount;
    private Integer curentPage;

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCurentPage() {
        return curentPage;
    }

    public void setCurentPage(Integer curentPage) {
        this.curentPage = curentPage;
    }
}
