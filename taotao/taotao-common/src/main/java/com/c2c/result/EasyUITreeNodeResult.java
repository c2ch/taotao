package com.c2c.result;

/**
 * Copyright: Copyright (c) 2018 cc
 *
 * @ClassName: EasyUITreeNodeResult
 * @Description: EasyUI树形返回结果类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/5 17:08
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class EasyUITreeNodeResult {

    private long id; //树节点ID
    private String text;//树节点名称
    private String state;//open表示是叶子节点，closed表示是父节点

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
