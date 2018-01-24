package com.c2c.search.controller;

import com.c2c.result.TaotaoResult;
import com.c2c.search.service.SearchItemSerivce;
import com.c2c.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: SearchItemController
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/23 14:48
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
public class SearchItemController {

    @Autowired
    private SearchItemSerivce searchItemSerivce;

    /**
     * 导入索引库
     * @return
     */
    @RequestMapping("/importAll")
    @ResponseBody
    public TaotaoResult importItems(){
        try {
            TaotaoResult result = searchItemSerivce.importItems();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 查询服务
     * @param keyword
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/q")
    @ResponseBody
    public TaotaoResult search(@RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "30") Integer rows){
        try {
            //转换字符集
            keyword = new String(keyword.getBytes("ISO8859-1"), "UTF-8");
            TaotaoResult result = searchItemSerivce.search(keyword, page, rows);
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

}
