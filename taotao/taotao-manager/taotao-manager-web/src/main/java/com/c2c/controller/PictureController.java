package com.c2c.controller;

import com.c2c.result.PictureResult;
import com.c2c.service.PictureService;
import com.c2c.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *
 * @ClassName: PictureController
 * @Description: 图片上传控制层
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/9 16:08
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Controller
@RequestMapping("/pic")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    //解决浏览器兼容性问题：
    //要求返回的数据是一个文本类型，要求content-type 为text/plan
    @RequestMapping("/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) {
        PictureResult result = pictureService.uploadImg(uploadFile);
        //需要把java对象手工转成json数据格式
        String json = JsonUtils.objectToJson(result);
        return json;
    }

}
