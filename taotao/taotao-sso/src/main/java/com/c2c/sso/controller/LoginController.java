package com.c2c.sso.controller;

import com.c2c.result.TaotaoResult;
import com.c2c.sso.service.LoginService;
import com.c2c.util.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginController
 * @Description: 登录的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/30 16:53
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * 登录接口
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(String username, String password,
            HttpServletRequest request, HttpServletResponse response){
        try {
            TaotaoResult result = loginService.login(username, password, request, response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 根据用户token获取用户信息
     * @param token
     * @return
     */
    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token,String callback){
        try {
            TaotaoResult result = loginService.getUserByToken(token);
            //支持jsonp调用
            if (StringUtils.isNotBlank(callback)) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 安全退出
     * @param token
     * @return
     */
    @RequestMapping("/user/logout/{token}")
    public String logout(@PathVariable String token,
                 HttpServletRequest request,HttpServletResponse response){
        loginService.logout(request, response, token);
        return "redirect:/page/login";

    }
}
