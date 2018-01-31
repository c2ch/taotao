package com.c2c.portal.service;

import com.c2c.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UserService
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/31 11:48
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface UserService {

    /**
     * 根据cookie中的token获取用户信息
     * @param request
     * @param response
     * @return
     */
    TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response);
}
