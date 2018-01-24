package com.c2c.test;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: TestJdeis
 * @Description: 测试Jedis的单机版和集群版
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/22 10:59
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class TestJedis {

    /**
     * 不使用连接池测试Jedis
     */
    @Test
    public void testJedisSingle(){
        //1、创建一个Jedis对象
        Jedis jedis = new Jedis("192.168.56.128",6379);
        jedis.set("jedis", "hello jedis");
        String result = jedis.get("jedis");
        System.out.println(result);
        jedis.close();//一定要关闭！！！！！
    }

    /**
     * 使用连接池测试Jedis
     */
    @Test
    public void testJedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();//连接池配置对象，可选。
        config.setMaxTotal(50);//最大连接数
        config.setMaxIdle(10);//空闲时池中最大连接数
        JedisPool jedisPool = new JedisPool(config,"192.168.56.128",6379);
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("jedis"));
        jedis.close();//一定要关闭！！！！！
        jedisPool.close();//系统关闭的时候关闭再关闭连接池
    }

    /**
     * 测试集群版
     */
    @Test
    public void testJedisCluster(){
        //在nodes中指定每个节点的地址
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.56.128",7001));
        nodes.add(new HostAndPort("192.168.56.128",7002));
        nodes.add(new HostAndPort("192.168.56.128",7003));
        nodes.add(new HostAndPort("192.168.56.128",7004));
        nodes.add(new HostAndPort("192.168.56.128",7005));
        nodes.add(new HostAndPort("192.168.56.128",7006));
        //jedisCluster在系统中是单例的。
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("name", "zhangsan");
        jedisCluster.set("value", "100");
        String name = jedisCluster.get("name");
        String value = jedisCluster.get("value");
        System.out.println(name);
        System.out.println(value);
        //系统关闭时再关闭jedisCluster
        jedisCluster.close();
    }


}
