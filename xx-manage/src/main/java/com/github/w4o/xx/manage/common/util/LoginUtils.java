package com.github.w4o.xx.manage.common.util;

import com.github.w4o.xx.manage.common.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 登录信息工具类
 *
 * @author Frank
 */
public class LoginUtils {

    /**
     * 获取登录信息
     *
     * @return 登录信息
     */
    public static UserInfo getLoginInfo() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取登录id
     *
     * @return 登录id
     */
    public static Long getLoginId() {
        return getLoginInfo().getUserId();
    }
}
