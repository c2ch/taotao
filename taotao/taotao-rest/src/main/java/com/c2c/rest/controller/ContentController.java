package com.c2c.rest.controller;

import com.c2c.pojo.TbContent;
import com.c2c.rest.service.ContentService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: CocntentController
 * @Description: content的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/18 10:42
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

    /**
     * 根据分类id查询内容列表
     * @param cid
     * @return
     */
    @RequestMapping("/{cid}")
    @ResponseBody
    public TaotaoResult getCotentList(@PathVariable long cid) {
        try {
            List<TbContent> list = contentService.getContentList(cid);
            return TaotaoResult.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            //如果调用服务出错返回一个结果给调用者
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 根据cid同步内容列表缓存
     * @param cid
     * @return
     */
    @RequestMapping("/sync/{cid}")
    @ResponseBody
    public TaotaoResult syncContentList(@PathVariable long cid) {
        try {
            TaotaoResult result = contentService.syncContentList(cid);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //如果调用服务出错返回一个结果给调用者
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

}
