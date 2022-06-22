package com.basiccrud.util;

import com.basiccrud.exception.CacheException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.concurrent.TimeUnit;

@Repository
@AllArgsConstructor
public class RedisRepository {

    private RedisTemplate<String,String> redisTemplate;
    private ObjectMapper objectMapper;
    @SneakyThrows
    public <T> void set(String key, T value, long timeout) {
        try {
           String jsonObject = objectMapper.writeValueAsString(value);
           redisTemplate.opsForValue().set(key,jsonObject);
           redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        } catch (RuntimeException e) {
            throw new RedisException("Error while saving to cache ", e);
        }
    }

    @SneakyThrows
    public <T> T get(Class<T> clazz, String key) {
        try {
            if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
                String jsonObject = redisTemplate.opsForValue().get(key);
                return objectMapper.readValue(jsonObject, clazz);
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            throw new RedisException("Error while retrieving from the cache ", e);
        }
    }

    public void remove(String key) {
        try {
            redisTemplate.delete(key);
        } catch (RuntimeException e) {
            throw new CacheException("Error while removing from the cache ", e);
            ///testdstdfstfsdt
        }
    }
}
