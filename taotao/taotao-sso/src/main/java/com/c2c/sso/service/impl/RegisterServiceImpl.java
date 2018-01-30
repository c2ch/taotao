package com.c2c.sso.service.impl;

import com.c2c.mapper.TbUserMapper;
import com.c2c.pojo.TbUser;
import com.c2c.pojo.TbUserExample;
import com.c2c.result.TaotaoResult;
import com.c2c.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: RegisterServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/30 14:48
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private TbUserMapper userMapper;
    /**
     * 校验数据是否合理
     * @param param
     * @param type
     * @return
     */
    @Override
    public TaotaoResult checkData(String param, Integer type) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        //type为类型，可选参数1、2、3分别代表username、phone、email
        if(type == 1){
            criteria.andUsernameEqualTo(param);
        }else if(type == 2){
            criteria.andPhoneEqualTo(param);
        } else if (type == 3) {
            criteria.andEmailEqualTo(param);
        }

        List<TbUser> tbUsers = userMapper.selectByExample(example);
        //如果不为空，则表示该数据库中已经存在，返回false
        if(!tbUsers.isEmpty()){
            return TaotaoResult.ok(false);
        }

        return TaotaoResult.ok(true);
    }

    /**
     * 注册用户
     * @param tbUser
     * @return
     */
    @Override
    public TaotaoResult register(TbUser tbUser) {
        //先校验数据
        //校验用户名和密码不能为空
        if(StringUtils.isBlank(tbUser.getUsername())
                || StringUtils.isBlank(tbUser.getPassword())){
            return TaotaoResult.build(400, "用户名和密码不能为空");
        }
        //校验数据是否重复
        TaotaoResult result = checkData(tbUser.getUsername(), 1);
        if(!(Boolean) result.getData()){
            return TaotaoResult.build(400, "用户名已存在");
        }
        //校验手机号
        if(tbUser.getPhone()!=null){
            result = checkData(tbUser.getPhone(), 2);
            if(!(boolean)result.getData()){
                return TaotaoResult.build(400, "手机号已存在");
            }
        }
        //校验邮箱
        if(tbUser.getEmail()!=null){
            result = checkData(tbUser.getEmail(), 3);
            if(!(boolean)result.getData()){
                return TaotaoResult.build(400, "邮箱已存在");
            }
        }

        //注册数据
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        //密码进行md5加密
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));

        userMapper.insert(tbUser);

        return TaotaoResult.ok("注册成功！");
    }
}
