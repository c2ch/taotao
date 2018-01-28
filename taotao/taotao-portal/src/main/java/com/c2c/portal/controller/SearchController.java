package com.c2c.portal.controller;

import com.c2c.portal.pojo.SearchResult;
import com.c2c.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: SearchController
 * @Description:搜索的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 17:17
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * 搜索
     * @param keyword
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam(value = "q") String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "30") int rows,
                         Model model){
        //get请求字符集转换
        try {
            keyword = new String(keyword.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            keyword = "";
            e.printStackTrace();
        }

        SearchResult result = searchService.search(keyword, page, rows);
        model.addAttribute("query", keyword);
        model.addAttribute("totalPages", result.getPageCount());
        model.addAttribute("itemList", result.getItemList());
        model.addAttribute("page", result.getCurentPage());
        //返回逻辑视图
        return "search";
    }
}
