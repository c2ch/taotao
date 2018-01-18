package com.c2c.rest.result;

import java.util.List;

/**
 * @ClassName: ItemCatResult
 * @Description: 商品分类的返回结果描述
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 14:55
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class ItemCatResult {

    private List data;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
