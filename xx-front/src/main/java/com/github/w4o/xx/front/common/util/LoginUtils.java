package com.github.w4o.xx.front.common.util;

import com.github.w4o.xx.front.common.LoginUser;
import lombok.val;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;

import static com.github.w4o.xx.core.constant.Constant.AUTH_USER;

/**
 * @author Frank
 */
public class LoginUtils {

    /**
     * 获取登录信息
     *
     * @return 登录信息
     */
    public static LoginUser getLoginInfo() {
        val requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new CustomException(ErrorCode.E401);
        }
        Object obj = requestAttributes.getAttribute(AUTH_USER, RequestAttributes.SCOPE_REQUEST);
        if (obj == null) {
            throw new CustomException(ErrorCode.E401);
        }
        return (LoginUser) obj;
    }

    /**
     * 获取登录id
     *
     * @return 登录id
     */
    public static long getLoginId() {
        return getLoginInfo().getUserId();
    }

    /**
     * 是否登录
     *
     * @return 登录 true，未登录 false
     */
    public static boolean isLogin() {
        val requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new CustomException(ErrorCode.E401);
        }
        Object obj = requestAttributes.getAttribute(AUTH_USER, RequestAttributes.SCOPE_REQUEST);
        return obj != null;
    }
}
