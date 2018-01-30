package com.c2c.portal.service.impl;

import com.c2c.pojo.TbItem;
import com.c2c.pojo.TbItemDesc;
import com.c2c.portal.service.FreeMarkerPageService;
import com.c2c.portal.service.ItemService;
import com.c2c.result.TaotaoResult;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FreeMarkerPageServiceImpl
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/30 9:46
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@Service
public class FreeMarkerPageServiceImpl implements FreeMarkerPageService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${STATIC_PAGE_PATH}")
    private String STATIC_PAGE_PATH;

    /**
     * 根据商品Id，调用rest服务，得到商品数据，生成静态页面。
     * @param itemId
     * @return TaotaoResult
     */
    @Override
    public TaotaoResult genHtml(Long itemId)throws Exception {
        //1、得到商品基本信息
        TbItem item = itemService.getItemById(itemId);
        //2、得到商品的基本信息描述内容
        TbItemDesc desc = itemService.getItemDescById(itemId);
        //3、得到商品的规格参数信息
        String param = itemService.getItemParamById(itemId);
        //4、创建模板对象
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("item.ftl");
        //5、创建数据集
        Map root = new HashMap();
        root.put("item", item);
        root.put("desc",desc.getItemDesc());
        root.put("itemParam", param);
        //6、创建一个Writer对象,指定生成的文件名
        Writer out = new FileWriter(new File(STATIC_PAGE_PATH+itemId+".html"));
        //7、生成静态文件
        template.process(root,out);
        //8、关闭流
        out.flush();
        out.close();
        return TaotaoResult.ok();
    }
}
