package com.c2c.sso.controller;

import com.c2c.pojo.TbUser;
import com.c2c.result.TaotaoResult;
import com.c2c.sso.service.RegisterService;
import com.c2c.util.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: RegisterController
 * @Description: 注册接口的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/30 14:57
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 校验数据是否合理
     * @param param
     * @param type
     * @param callback 支持jsonp调用
     * @return
     */
    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param,
                                  @PathVariable Integer type,
                                  String callback){
        try {
            TaotaoResult result = registerService.checkData(param, type);
            if(StringUtils.isNotBlank(callback)){
                //如果不为空，需要支持jsonp调用
                MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
                jacksonValue.setJsonpFunction(callback);
                return jacksonValue;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 注册用户
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "/user/register", method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser tbUser){
        try {
            TaotaoResult result = registerService.register(tbUser);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

    }
}
