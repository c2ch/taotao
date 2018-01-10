package com.c2c.result;

/**
 * Copyright: Copyright (c) 2018
 *
 * @ClassName: PictureResult
 * @Description: 利用富文本编辑器上传图片返回的结果类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/9 16:18
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class PictureResult {

    //参考文档http://kindeditor.net/docs/upload.html
    private int error;//错误码
    private String url;//返回的url
    private String message;//返回的信息

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
