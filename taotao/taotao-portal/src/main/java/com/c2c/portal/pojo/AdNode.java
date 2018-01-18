package com.c2c.portal.pojo;

/**
 * @ClassName: AdNode
 * @Description: 广告位的pojo
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/18 11:14
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class AdNode {

    private String src;
    private int width;
    private int height;
    private String srcB;
    private int widthB;
    private int heightB;
    private String href;
    private String alt;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSrcB() {
        return srcB;
    }

    public void setSrcB(String srcB) {
        this.srcB = srcB;
    }

    public int getWidthB() {
        return widthB;
    }

    public void setWidthB(int widthB) {
        this.widthB = widthB;
    }

    public int getHeightB() {
        return heightB;
    }

    public void setHeightB(int heightB) {
        this.heightB = heightB;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
