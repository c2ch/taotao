package com.c2c.portal.controller;

import com.c2c.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *
 * @ClassName: IndexController
 * @Description: 首页的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 11:19
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model){
        //取大广告位内容
        String result = contentService.getAdList();
        //传递给页面, var data = ${ad1};
        model.addAttribute("ad1", result);
        return "index";
    }

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    @ResponseBody
    public String post(String name,String pass){
        System.out.println(name+":"+pass);
        return "Ok";
    }
}
