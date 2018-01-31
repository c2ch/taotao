package com.c2c.portal.interceptor;

import com.c2c.pojo.TbUser;
import com.c2c.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginInterceptor
 * @Description: 登录拦截器
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/31 14:18
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;

    /**
     * controller之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 1、拦截请求url
        // 2、从cookie中取token
        // 3、如果没有toke跳转到登录页面。
        // 4、取到token，需要调用sso系统的服务查询用户信息。
        TbUser user = userService.getUserByToken(httpServletRequest, httpServletResponse);
        if (user == null) {
            //用户没有登录，跳转到登录页面
            //判断用户当前访问页面的url，并返回到登录页面登录后直接跳转到之前访问的页面
            httpServletResponse.sendRedirect(SSO_LOGIN_URL + "?redirect="
                    + httpServletRequest.getRequestURL());
            return false;
        }


        //放行
        return true;
    }

    /**
     * 在controller执行之后，返回ModelAndView之前拦截
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在controller全部执行完之后拦截
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
