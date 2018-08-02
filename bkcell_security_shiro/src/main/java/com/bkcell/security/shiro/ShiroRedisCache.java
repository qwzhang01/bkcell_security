package com.bkcell.security.shiro;

import com.bkcell.security.common.kit.ByteKit;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;

public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private String keyPrefix = "shiro_redis_session:";
    private org.springframework.cache.Cache cache;

    public ShiroRedisCache(org.springframework.cache.Cache springCache) {
        this.cache = springCache;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    private String genKey(K key) {
        return (keyPrefix + new String(ByteKit.toByte(key)));
    }

    @Override
    public V get(K key) throws CacheException {
        if (key == null) {
            return null;
        }
        org.springframework.cache.Cache.ValueWrapper valueWrapper = cache.get(genKey(key));
        if (valueWrapper == null) {
            return null;
        }
        V v = (V) valueWrapper.get();
        return v;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        cache.put(genKey(key), value);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        V v = (V) cache.get(genKey(key)).get();
        cache.evict(genKey(key));
        return v;
    }

    @Override
    public void clear() throws CacheException {
        cache.clear();
    }

    @Override
    public int size() {
        throw new RuntimeException("");
    }

    @Override
    public Set<K> keys() {
        throw new RuntimeException("");
    }

    @Override
    public Collection<V> values() {
        throw new RuntimeException("");
    }
}
