package com.c2c.sso.service;

import com.c2c.result.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginService
 * @Description: 登录的Service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/30 15:58
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface LoginService {

    /**
     * 登录接口
     * @param username
     * @param password
     * @param request
     * @param response
     * @return TaotaoResult
     */
    TaotaoResult login(String username, String password,
                       HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据用户token获取用户信息
     * @param token
     * @return
     */
    TaotaoResult getUserByToken(String token);

    /**
     * 安全退出
     * @param token
     * @return
     */
    TaotaoResult logout(HttpServletRequest request,
                        HttpServletResponse response,String token);
}
