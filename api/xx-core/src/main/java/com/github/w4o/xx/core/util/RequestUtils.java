package com.github.w4o.xx.core.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求工具类
 *
 * @author Frank
 */
public class RequestUtils {

    private static final String UNKNOWN_REMOTE_ADDRESS = "unknown";
    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"
    };

    /**
     * 获取用户IP
     *
     * @param request HttpServletRequest
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && !ip.isEmpty() && !UNKNOWN_REMOTE_ADDRESS.equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取用户IP， 通过RequestContextHolder
     *
     * @return ip地址
     */
    public static String getIpAddress() {
        val requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return StringUtils.EMPTY;
        }
        val servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        val httpServletRequest = servletRequestAttributes.getRequest();
        return getIpAddress(httpServletRequest);
    }

    /**
     * 获取请求User-Agent信息
     *
     * @return User-Agent信息
     */
    public static String getUserAgent() {
        val requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return StringUtils.EMPTY;
        }
        val servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        val httpServletRequest = servletRequestAttributes.getRequest();
        return httpServletRequest.getHeader("user-agent");
    }

    /**
     * 获取请求方法
     *
     * @return 请求方法
     */
    public static String getRequestMethod() {
        val requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return StringUtils.EMPTY;
        }
        val servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        val httpServletRequest = servletRequestAttributes.getRequest();
        return httpServletRequest.getMethod();
    }
}
