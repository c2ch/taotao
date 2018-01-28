package com.c2c.portal.pojo;

import com.c2c.pojo.TbItem;

/**
 * @ClassName: PortalItem
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/26 17:07
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class PortalItem extends TbItem {

    public String[] getImages(){
        String images = this.getImage();
        if(images!=null && !images.equals("")){
            String[] split = images.split(",");
            return split;
        }
        return  null;
    }
}
