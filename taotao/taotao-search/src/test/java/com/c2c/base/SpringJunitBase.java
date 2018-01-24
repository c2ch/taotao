package com.c2c.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *  cc
 *
 * @ClassName: SpringJunitBase
 * @Description: 搭建spring的单元测试类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/4 14:59
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试
@ContextConfiguration(locations={"classpath*:spring/applicationContext-*.xml"}) //加载配置文件
//==如果加入以下代码，所有继承该类的测试类都会遵循该配置；也可以不加，在测试类的方法上再具体配置==
public class SpringJunitBase extends AbstractJUnit4SpringContextTests {
}
