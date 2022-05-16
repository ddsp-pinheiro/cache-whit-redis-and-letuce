package com.basiccrud.util;

import com.basiccrud.exception.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository<T> {

    private long ttl;
    private StringRedisTemplate redisTemplate;
    private ValueOperations<String, String> valueOps;

    @Autowired
    public RedisRepository(StringRedisTemplate redisTemplate,
                              @Value("${spring.redis.timeToLive}") long ttl) {
        this.redisTemplate = redisTemplate;
        valueOps = redisTemplate.opsForValue();
        this.ttl = ttl;
    }

    /**
     * Save the key value pair in cache with a ttl
     *
     * @param key   cache key
     * @param value cache value
     */

    public void put(String key, T value) {
        try {
            valueOps.set(key, String.valueOf(value));
            redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
        } catch (RuntimeException e) {
            throw new CacheException("Error while saving to cache ", e);
        }
    }

    /**
     * Returns the cached value
     *
     * @param key cached key
     * @return cached value
     */
    public T  get(String key) {
        try {
            Boolean b = redisTemplate.hasKey(key);
            if (Boolean.TRUE.equals(b)) {
                return (T) Optional.ofNullable(valueOps.get(key));
            } else {
                return (T) Optional.empty();
            }
        } catch (RuntimeException e) {
            throw new CacheException("Error while retrieving from the cache ", e);
        }
    }

    /**
     * Remove the cached value
     *
     * @param key cached key
     */
    public void remove(String key) {
        try {
            redisTemplate.delete(key);
        } catch (RuntimeException e) {
            throw new CacheException("Error while removing from the cache ", e);
        }
    }
}
