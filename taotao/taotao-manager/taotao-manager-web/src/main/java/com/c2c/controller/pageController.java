package com.c2c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright: Copyright (c) 2018 linewell
 *
 * @ClassName: pageController
 * @Description: 页面展示的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/4 11:10
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
public class pageController {

    /**
     * 展示后台首页
     * @return
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
