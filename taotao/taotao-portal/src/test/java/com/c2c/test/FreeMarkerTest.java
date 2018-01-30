package com.c2c.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FreeMarkerTest
 * @Description: 测试FreeMarker
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/28 18:09
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class FreeMarkerTest {

    /**
     * 测试FreeMarker使用方法
     * @throws Exception
     */
    @Test
    public void testFreemarekr() throws Exception{
        //1、创建Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //2、告诉configuration对象模板文件存放的路径
        configuration.setDirectoryForTemplateLoading(new File("E:\\GitProject\\" +
                "repository\\taotao\\taotao-portal\\src\\main\\webapp\\WEB-INF\\" +
                "ftl"));
        //3、设置字符集
        configuration.setDefaultEncoding("UTF-8");
        //4、从configuration对象中获得模板对象，需要制定模板文件的名字
        Template template = configuration.getTemplate("hello.ftl");
        //5、创建数据集，可以是一个map对象，也可以是一个pojo，把模板所需要的数据都放入数据集
        Map root = new HashMap<>();
        root.put("hello", "nihao freemarker");
        //6、创建一个Writer对象，指定生成的文件的文件名和路径
        Writer out = new FileWriter(new File("E:\\ftl\\html\\hello.html"));
        //7、调用模板对象的Process方法生成静态文件，需要两个参数
        template.process(root, out);
        //8、关闭流
        out.flush();
        out.close();
    }
}
