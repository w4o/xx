package com.github.w4o.xx.core.cache;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * 自定义缓存管理器
 *
 * @author Frank
 */
public class CustomCacheManager extends RedisCacheManager {
    public CustomCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @NonNull
    @Override
    protected RedisCache createRedisCache(@NonNull String name, RedisCacheConfiguration cacheConfig) {
        String[] array = StringUtils.delimitedListToStringArray(name, "#");
        name = array[0];
        // 解析TTL
        if (array.length > 1) {
            long ttl = Long.parseLong(array[1]);
            // 注意单位我此处用的是秒，而非毫秒
            cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
        }
        return super.createRedisCache(name, cacheConfig);
    }
}
