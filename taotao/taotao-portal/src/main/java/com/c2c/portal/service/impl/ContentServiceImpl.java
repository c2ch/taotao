package com.c2c.portal.service.impl;

import com.c2c.pojo.TbContent;
import com.c2c.portal.pojo.AdNode;
import com.c2c.portal.service.ContentService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.HttpClientUtil;
import com.c2c.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ContentServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/18 11:25
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;

    @Value("${REST_CONTENT_AD1_CID}")
    private String REST_CONTENT_AD1_CID;

    /**
     * 调用taotao-rest服务，得到大广告位的列表
     * @return
     */
    @Override
    public String getAdList() {
        //调用服务取得大广告位内容
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_URL + REST_CONTENT_AD1_CID);

        //把json字符串转成pojo对象
        TaotaoResult result = TaotaoResult.formatToList(json, TbContent.class);

        //取data属性
        List<TbContent> contentList = (List<TbContent>)result.getData();

        //构建resultList
        List<AdNode> resultList = new ArrayList<>();

        //遍历list，构建adnode对象
        for (TbContent tbContent : contentList) {
            AdNode an = new AdNode();
            an.setSrc(tbContent.getPic());
            an.setHeight(240);
            an.setWidth(670);

            an.setSrcB(tbContent.getPic2());
            an.setHeightB(240);
            an.setWidthB(550);

            an.setAlt(tbContent.getSubTitle());
            an.setHref(tbContent.getUrl());
            resultList.add(an);
        }

        //把resultList转成json串返回
        return JsonUtils.objectToJson(resultList);
    }
}
