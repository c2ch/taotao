package com.c2c.sso.service.impl;

import com.c2c.mapper.TbUserMapper;
import com.c2c.pojo.TbUser;
import com.c2c.pojo.TbUserExample;
import com.c2c.result.TaotaoResult;
import com.c2c.sso.component.JedisClient;
import com.c2c.sso.service.LoginService;
import com.c2c.util.CookieUtils;
import com.c2c.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: LoginServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/30 16:16
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;
    @Value("${REDIS_SESSION_EXPIRE}")
    private Integer REDIS_SESSION_EXPIRE;
    @Value("${SESSION_ID_NAME}")
    private String SESSION_ID_NAME;

    /**
     * 登录接口
     * @param username
     * @param password
     * @param request
     * @param response
     * @return TaotaoResult
     */
    @Override
    public TaotaoResult login(String username, String password,
             HttpServletRequest request, HttpServletResponse response) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        //查询
        List<TbUser> users = tbUserMapper.selectByExample(example);

        if(users.isEmpty()){
            return TaotaoResult.build(400, "用户名错误");
        }

        //校验密码
        TbUser user = users.get(0);
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            return TaotaoResult.build(400, "密码错误");
        }

        //登录成功，
        //生成token
        String token = UUID.randomUUID().toString();
        // 把token信息写入到redis中
        user.setPassword(null);//为了安全
        jedisClient.set(REDIS_SESSION_KEY+":"+token, JsonUtils.objectToJson(user));
        //设置Session的过期时间
        jedisClient.expire(REDIS_SESSION_KEY + ":" + token, REDIS_SESSION_EXPIRE);
        // 写入到cookie中
        CookieUtils.setCookie(request,response,SESSION_ID_NAME,token);

        return TaotaoResult.ok(token);
    }

    /**
     * 根据用户token获取用户信息
     * @param token
     * @return
     */
    @Override
    public TaotaoResult getUserByToken(String token) {

        String value = jedisClient.get(REDIS_SESSION_KEY + ":" + token);
        if(StringUtils.isBlank(value)){
            return TaotaoResult.build(400, "用户Session已经过期了");
        }
        //转成java对象
        TbUser tbUser = JsonUtils.jsonToPojo(value, TbUser.class);
        //更新session过期时间
        jedisClient.expire(REDIS_SESSION_KEY + ":" + token, REDIS_SESSION_EXPIRE);

        return TaotaoResult.ok(tbUser);
    }

    /**
     * 安全退出
     * @param token
     * @return
     */
    @Override
    public TaotaoResult logout(HttpServletRequest request,
                               HttpServletResponse response,String token) {
        jedisClient.del(REDIS_SESSION_KEY + ":" + token);
        CookieUtils.deleteCookie(request,response,SESSION_ID_NAME);
        return TaotaoResult.ok();
    }
}
