package com.c2c.controller;

import com.c2c.pojo.TbContent;
import com.c2c.result.EasyUIDataGridResult;
import com.c2c.result.TaotaoResult;
import com.c2c.service.ContentService;
import com.c2c.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ContentController
 * @Description: 内容的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 23:48
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;



    /**
     * 根据内容分类id分页查询内容信息
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(long categoryId,Integer page, Integer rows) {
        EasyUIDataGridResult result = contentService.getContentList(categoryId,page, rows);
        return  result;
    }

    /**
     * 新增内容
     * @param content
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent content) {
        TaotaoResult result = contentService.insertContent(content);
        //需要进行缓存的同步
        HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_URL+content.getCategoryId());
        return result;
    }
}
