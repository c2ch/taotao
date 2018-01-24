package com.c2c.rest.component.impl;

import com.c2c.rest.component.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * @ClassName: JedisClusterimpl
 * @Description:Jdeis集群版实现类
 * @version: v1.0.0
 * @author: cc
 * @date: 2018/1/22 11:42
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 */
public class JedisClusterimpl implements JedisClient {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key,value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key,field,value);
    }

    @Override
    public String hget(String key, String field) {
        return jedisCluster.hget(key,field);
    }

    @Override
    public Long hdel(String key, String field) {
        return jedisCluster.hdel(key,field);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    @Override
    public Long expire(String key, int second) {
        return jedisCluster.expire(key,second);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }
}
