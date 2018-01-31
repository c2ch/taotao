package com.c2c.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: PageController
 * @Description: 展示页面的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/31 9:40
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
public class PageController {
    /**
     * 展示登录页面
     * @return
     */
    @RequestMapping("/page/login")
    public String showLogin(String redirect,Model model){
        //判断用户之前访问的url
        model.addAttribute("redirect", redirect);
        return "login";
    }

    /**
     * 展示注册页面
     * @return
     */
    @RequestMapping("/page/register")
    public String showRegister(){
        return "register";
    }
}
