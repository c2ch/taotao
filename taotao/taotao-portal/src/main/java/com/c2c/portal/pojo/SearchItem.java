package com.c2c.portal.pojo;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: SearchItem
 * @Description: 搜索结果对应的pojo
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 11:56
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class SearchItem {

    private String id;
    private String title;
    private String sellPoint;
    private Long price;
    private String image;
    private String categoryName;
    private String itemDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        if(StringUtils.isNotBlank(image)){
            String[] strings = image.split(",");
            return strings[0];
        }
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
