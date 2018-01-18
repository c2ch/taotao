package com.c2c.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @ClassName: ItemCatNode
 * @Description: 描述商品分类前台页面展示的一个POJO类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 14:50
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class ItemCatNode {

    @JsonProperty("u")
    private String url;//对应商品分类json中的u
    @JsonProperty("n")
    private String name;//对应商品分类json中的n
    @JsonProperty("i")
    private List items;//对应商品分类json中的i

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
