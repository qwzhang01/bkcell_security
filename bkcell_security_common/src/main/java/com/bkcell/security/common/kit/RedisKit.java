package com.bkcell.security.common.kit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedisKit {

    private static RedisKit redisKit;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public static <V> V get(String key) {
        V v = (V) redisKit.redisTemplate.opsForValue().get(key);
        return v;
    }

    public static <V> void set(String key, V value) {
        redisKit.redisTemplate.opsForValue().set(key, value);
    }

    public static HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    public static ValueOperations<String, Object> valueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisKit.redisTemplate.opsForValue();
    }

    public static ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisKit.redisTemplate.opsForList();
    }

    public static SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisKit.redisTemplate.opsForSet();
    }

    public static ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisKit.redisTemplate.opsForZSet();
    }

    @PostConstruct
    public void init() {
        redisKit = this;
        redisKit.redisTemplate = this.redisTemplate;
    }
}