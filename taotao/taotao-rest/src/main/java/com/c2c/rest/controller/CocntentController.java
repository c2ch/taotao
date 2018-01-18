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
public class CocntentController {

    @Autowired
    private ContentService contentService;


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


}
