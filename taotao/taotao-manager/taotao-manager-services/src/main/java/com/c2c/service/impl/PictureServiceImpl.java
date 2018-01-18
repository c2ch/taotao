package com.c2c.service.impl;

import com.c2c.common.Constants;
import com.c2c.result.PictureResult;
import com.c2c.service.PictureService;
import com.c2c.util.FastDFSClientUtil;
import com.c2c.util.PropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *
 * @ClassName: PictureServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/9 16:24
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class PictureServiceImpl implements PictureService {

    //上传图片
    @Override
    public PictureResult uploadImg(MultipartFile uploadFile) {
        PictureResult result = new PictureResult();
        if(uploadFile.isEmpty()){
            result.setError(1);
            result.setMessage("图片为空");
            return result;
        }
        //上传到图片服务器
        try {
            String originalName = uploadFile.getOriginalFilename();
            //文件扩展名，不带“.”
            String extName = originalName.substring(originalName.lastIndexOf(".")+1);
            FastDFSClientUtil fastDFSClientUtil = new FastDFSClientUtil("classpath:properties/client.conf");
           //url:group1/M00/00/00/wKg4gFpUpMiAXGioAABdrZgsqUU004_big.jpg
            String url = fastDFSClientUtil.uploadFile(uploadFile.getBytes(),extName );
            //拼接url地址
            url = PropertiesUtil.getInstance().getValue("client.properties", Constants.IMAGE_SERVER,null)+url;

            //0代表上传成功
            result.setError(0);
            result.setUrl(url);

        } catch (Exception e) {
            e.printStackTrace();
            result.setError(1);
            result.setMessage("上传失败");
        }
        return result;
    }
}
