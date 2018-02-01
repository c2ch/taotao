package com.c2c.order.component;

/**
 * @ClassName: JedisClient
 * @Description: Jedis客户端的的接口
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/22 11:24
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public interface JedisClient {

    String set(String key, String value);

    String get(String key);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hdel(String key, String field);

    Long del(String key);

    Long incr(String key);

    Long decr(String key);

    //设置过期时间
    Long expire(String key, int second);

    //查看还剩多少时间
    Long ttl(String key);

}
