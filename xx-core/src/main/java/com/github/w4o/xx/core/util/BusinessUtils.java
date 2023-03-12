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
    public String aesDecrypt(String key, String data) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), key.getBytes());
        String decrypt = "";
        try {
            decrypt = aes.decryptStr(data);
        } catch (Exception ignore) {
        }
        return decrypt;
    }
}
