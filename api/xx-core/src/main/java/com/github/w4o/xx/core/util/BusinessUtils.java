package com.github.w4o.xx.core.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 业务工具类
 *
 * @author Frank
 */
@Slf4j
@Component
public class BusinessUtils {

    private static String active;
    private static Boolean encrypted = false;
    private static String aesKey = "a1b2c3d4e5f6g7h8";

    @Value("${spring.profiles.active}")
    public void setActive(String active) {
        BusinessUtils.active = active;
    }

    @Value("${app.encrypted}")
    public void setEncrypted(Boolean encrypted) {
        BusinessUtils.encrypted = encrypted;
    }

    @Value("${app.aes-key}")
    public void setAesKey(String aesKey) {
        BusinessUtils.aesKey = aesKey;
    }

    public static boolean isDebug() {
        log.debug("active:{}", active);
        return Arrays.asList(new String[]{"dev", "test", "stage"}).contains(active);
    }

    /**
     * AES 解密
     *
     * @param key  密钥
     * @param data 密文
     * @return 明文
     */
    @Deprecated
    public String aesDecrypt(String key, String data) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), key.getBytes());
        String decrypt = "";
        try {
            decrypt = aes.decryptStr(data);
        } catch (Exception ignore) {
        }
        return decrypt;
    }

    /**
     * AES 解密
     *
     * @param data 密文
     * @return 明文
     */
    public static String aesDecrypt(String data) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, aesKey.getBytes(), aesKey.getBytes());
        String decrypt = "";
        try {
            decrypt = aes.decryptStr(data);
        } catch (Exception ignore) {
        }
        return decrypt;
    }

    /**
     * 解密数据
     *
     * @param data 数据
     * @return 结果
     */
    public static String decrypt(String data) {
        if (encrypted) {
            return BusinessUtils.aesDecrypt(data);
        } else {
            return data;
        }
    }
}
