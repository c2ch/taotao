package com.c2c.util;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Copyright: Copyright (c) 2018
 *
 * @ClassName: PropertiesUtil
 * @Description: 属性文件的工具类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/9 16:58
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class PropertiesUtil {

    Logger logger = Logger.getLogger(PropertiesUtil.class);

    private static PropertiesUtil propertiesUtil;

    private PropertiesUtil() {
    }

    public static PropertiesUtil getInstance() {
        if (null == propertiesUtil) {
            propertiesUtil = new PropertiesUtil();
        }
        return propertiesUtil;
    }

    /**
     * @Function: PropertiesUtil ::loadProp
     * @Description:
     * @param:[path]properties文件路径
     * @version: v1.0.0
     * @author: cc
     * @date: 2018/1/9 17:13
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * -------------------------------------------------------------
     */
    public Properties loadProp(String path) {
        InputStream in = null;
        Properties prop = null;
        BufferedInputStream bis = null;
        try {
            prop = new Properties();
            in = this.getClass().getResourceAsStream("/" + path);
            prop.load(in);
        } catch (FileNotFoundException e) {
            logger.info("配置文件不存在");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

    /**
     * 获取proerties的值
     * @param path
     * @param key
     * @param defaultValue
     * @return
     */
    public String getValue(String path,String key,String defaultValue){
       Properties properties = propertiesUtil.loadProp(path);
        return properties.getProperty(key, defaultValue);
    }

}
