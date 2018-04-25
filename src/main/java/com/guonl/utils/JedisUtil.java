package com.guonl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.Set;

/**
 * Created by guonl
 * Date 2018/4/24 下午10:31
 * Description:
 */
@Component
public class JedisUtil {

    @Autowired
    private JedisPool jedisPool;

    private Jedis getResource(){
        return jedisPool.getResource();
    }


    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = this.getResource();
        try {
            jedis.set(key,value);
        } finally {
            jedis.close();
        }
        return value;
    }

    public void expire(byte[] key, int i) {
        Jedis jedis = this.getResource();
        try {
            jedis.expire(key,i);
        } finally {
            jedis.close();
        }
    }


    public byte[] get(byte[] key) {
        Jedis jedis = this.getResource();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }


    public void del(byte[] key) {
        Jedis jedis = this.getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String shiro_session_prefix) {
        Jedis jedis = this.getResource();
        try {
            return jedis.keys((shiro_session_prefix + "*").getBytes());
        } finally {
            jedis.close();
        }
    }
}
