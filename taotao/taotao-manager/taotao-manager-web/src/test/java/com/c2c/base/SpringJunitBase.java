package com.c2c.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright: Copyright (c) 2018 cc
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

//@Transactional非常关键，如果不加入这个注解配置，事务控制就会完全失效！
@Transactional
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时
//指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class SpringJunitBase extends AbstractJUnit4SpringContextTests {
}
