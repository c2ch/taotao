package com.c2c.test;

import com.c2c.base.SpringJunitBase;
import com.c2c.rest.component.JedisClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: TestSpringJedis
 * @Description:
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/22 11:52
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class TestSpringJedis extends SpringJunitBase{

    @Autowired
    private JedisClient jedisClient;

    @Test
    public void testJdeidsSingle(){
        System.out.println(jedisClient.get("jedis"));
    }

}
