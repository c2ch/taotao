package com.c2c.portal.service;

import com.c2c.result.TaotaoResult;

/**
 * @ClassName: FreeMarkerPageService
 * @Description: 使用FreeMarker生成的静态文件的service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/30 9:44
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface FreeMarkerPageService {

    /**
     * 根据商品Id，调用rest服务，得到商品数据，生成静态页面。
     * @param itemId
     * @return TaotaoResult
     */
    TaotaoResult genHtml(Long itemId) throws Exception;
}
