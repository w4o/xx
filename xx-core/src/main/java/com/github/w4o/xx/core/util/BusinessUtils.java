package com.github.w4o.xx.core.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 业务工具类
 *
 * @author Frank
 */
@Component
public class BusinessUtils {

    @Value("${spring.profiles.active}")
    private String active;
    @Value("${app.encrypted}")
    private Boolean encrypted = false;
    @Value("${app.aes-key}")
    private String aesKey = "a1b2c3d4e5f6g7h8";

    public boolean isDebug() {
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
    public String aesDecrypt(String data) {
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
    public String decrypt(String data) {
        if (encrypted) {
            return this.aesDecrypt(data);
        } else {
            return data;
        }
    }
}
