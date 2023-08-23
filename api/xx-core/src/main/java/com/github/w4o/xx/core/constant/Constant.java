package com.github.w4o.xx.core.constant;

/**
 * 系统常量类
 *
 * @author Frank
 */
public interface Constant {
    String JWT_HEADER_NAME = "Authorization";
    String AUTH_USER = "_jwt_auth_user";
    /**
     * Redis缓存前缀
     */
    String REDIS_CACHE_PREFIX = "caching:";

    /**
     * 短信缓存前缀
     */
    String REDIS_SMS_CODE_PREFIX = "sms:code:";
}
