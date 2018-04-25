package com.guonl.shiro.cache;

import com.guonl.utils.JedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * Created by guonl
 * Date 2018/4/25 下午9:54
 * Description:
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    @Resource
    private JedisUtil jedisUtil;

    private final String CAHCE_PREFIX = "guonl-cahce:";

    private byte[] getKey(K k) {
        if (k instanceof String) {
            return (CAHCE_PREFIX + k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    @Override
    public V get(K k) throws CacheException {
        byte[] vaule = jedisUtil.get(getKey(k));
        if(vaule != null){
            System.out.println("从缓存中获取授权数据");
            return (V) SerializationUtils.deserialize(vaule);
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        byte[] key = getKey(k);
        byte[] vaule = SerializationUtils.serialize(v);
        jedisUtil.set(key,vaule);
        jedisUtil.expire(key,600);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = jedisUtil.get(key);
        jedisUtil.del(key);
        if(value != null){
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
