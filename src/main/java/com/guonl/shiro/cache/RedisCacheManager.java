package com.guonl.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;

/**
 * Created by guonl
 * Date 2018/4/25 下午9:52
 * Description: shiro的缓存机制
 */
public class RedisCacheManager implements CacheManager{


    @Resource
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisCache;
    }
}
