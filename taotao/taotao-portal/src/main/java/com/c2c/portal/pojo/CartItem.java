package com.c2c.portal.pojo;

/**
 * @ClassName: CartItem
 * @Description: 购物车的商品pojo
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/31 15:38
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class CartItem {

    private Long id;

    private String title;

    private Long price;

    private Integer num;

    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
