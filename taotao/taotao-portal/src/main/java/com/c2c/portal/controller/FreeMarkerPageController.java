package com.c2c.portal.controller;

import com.c2c.portal.service.FreeMarkerPageService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: FreeMarkerPageController
 * @Description: Freemarker生成的页面的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/30 10:19
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
public class FreeMarkerPageController {

    @Autowired
    private FreeMarkerPageService freeMarkerPageService;

    /**
     * 生成静态页面
     * @param itemId
     * @return
     */
    @RequestMapping("/gen/page/{itemId}")
    @ResponseBody
    public TaotaoResult genStaticHtml(@PathVariable Long itemId){
        try {
            TaotaoResult result = freeMarkerPageService.genHtml(itemId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
