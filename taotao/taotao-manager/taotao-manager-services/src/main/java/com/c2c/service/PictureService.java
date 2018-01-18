package com.c2c.service;

import com.c2c.result.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *
 * @ClassName: PictureService
 * @Description: 图片的service
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/9 16:21
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface PictureService {
    //上传图片
    PictureResult uploadImg(MultipartFile uploadFile);
}
