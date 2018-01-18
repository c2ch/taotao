package com.c2c.rest.controller;

import com.c2c.rest.result.ItemCatResult;
import com.c2c.rest.service.ItemCatService;
import com.c2c.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ItemCatController
 * @Description: 商品分类的Controller
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/16 15:24
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
@RequestMapping("/itemcat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    //表示content-type为application-json;charset=utf-8
    @RequestMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemCat(@RequestParam("callback") String callback){
        ItemCatResult result = itemCatService.getItemCatList();

        //把集合转成json串
        String json = JsonUtils.objectToJson(result);

        if(StringUtils.isBlank(callback)){
            return json;
        }
        //如果字符串不为空，需要支持jsonp调用
        return callback + "("+json+");";

//        第二种方法：
//        要求springmvc必须是4.1以上版本。
       /* List result = itemCatService.getItemCatList();
        String json = JsonUtils.objectToJson(result);
        if (StringUtils.isBlank(callback)) {
            //需要把result转换成字符串
            return json;
        }
        //如果字符串不为空，需要支持jsonp调用
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;*/


    }

}
