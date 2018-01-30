package com.c2c.sso.service;

import com.c2c.pojo.TbUser;
import com.c2c.result.TaotaoResult;

/**
 * @ClassName: RegisterService
 * @Description: 注册接口的Service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/30 14:46
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface RegisterService {

    /**
     * 校验数据是否合理
     * @param param
     * @param type
     * @return
     */
    TaotaoResult checkData(String param,Integer type);

    /**
     * 注册用户
     * @param tbUser
     * @return
     */
    TaotaoResult register(TbUser tbUser);
}
