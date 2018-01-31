package com.c2c.portal.service.impl;

import com.c2c.pojo.TbUser;
import com.c2c.portal.service.UserService;
import com.c2c.result.TaotaoResult;
import com.c2c.util.CookieUtils;
import com.c2c.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/31 11:52
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${SESSION_ID_NAME}")
    private String SESSION_ID_NAME;
    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${USER_INFO_URL}")
    private String USER_INFO_URL;

    /**
     * 根据cookie中的token获取用户信息
     * @param request
     * @param response
     * @return
     */
    @Override
    public TbUser getUserByToken(HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            //从cookie中获取token
            String token = CookieUtils.getCookieValue(request, SESSION_ID_NAME);
            if(token==null){
                //说明没有用户信息
                return null;
            }
            String json = HttpClientUtil.doGet(SSO_BASE_URL + USER_INFO_URL + token);
            //把json转换成java对象
            TaotaoResult result = TaotaoResult.format(json);
            if (result.getStatus() != 200) {
                return null;
            }
            //取用户对象
            result = TaotaoResult.formatToPojo(json, TbUser.class);
            //转成java对象
            TbUser tbuser = (TbUser)result.getData();
            return tbuser;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
