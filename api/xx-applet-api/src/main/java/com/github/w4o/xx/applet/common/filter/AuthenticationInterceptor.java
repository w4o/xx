package com.github.w4o.xx.applet.common.filter;

import com.github.w4o.xx.applet.common.util.JwtUtils;
import com.github.w4o.xx.core.annotation.CheckToken;
import com.github.w4o.xx.core.constant.Constant;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import java.lang.reflect.Method;

import static com.github.w4o.xx.core.constant.Constant.AUTH_USER;


/**
 * @author Frank
 */
@Slf4j
public class AuthenticationInterceptor implements AsyncHandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull Object object) {

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        Method method = handlerMethod.getMethod();

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(CheckToken.class)) {
            CheckToken checkToken = method.getAnnotation(CheckToken.class);

            // 从 http 请求头中取出 token
            String token = httpServletRequest.getHeader(Constant.JWT_HEADER_NAME);

            // 如果 required 是true，token要严格验证；反之，如果有token就验证，没有不验证
            if (checkToken.required()) {

                if (StringUtils.isEmpty(token)) {
                    throw new CustomException(ErrorCode.E401);
                }

                httpServletRequest.setAttribute(AUTH_USER, jwtUtils.getLoginUserFromToken(token));
            } else {
                if (StringUtils.isNotEmpty(token)) {
                    httpServletRequest.setAttribute(AUTH_USER, jwtUtils.getLoginUserFromToken(token));
                }
            }
        }
        return true;
    }
}