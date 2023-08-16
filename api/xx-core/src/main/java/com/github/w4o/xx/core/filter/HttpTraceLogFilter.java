package com.github.w4o.xx.core.filter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.w4o.xx.core.util.RequestUtils;
import io.micrometer.core.instrument.util.IOUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HTTP请求日志过滤器
 *
 * @author Frank
 */
@Slf4j
public class HttpTraceLogFilter extends OncePerRequestFilter implements Ordered {
    private static final String NEED_TRACE_PATH_PREFIX = "/";
    private static final String[] IGNORE_TRACE_PATH_PREFIX = {"/v3/api-docs", "/swagger-ui", "/webjars", "/doc.html", "/favicon.ico"};
    private static final String IGNORE_CONTENT_TYPE = "multipart/form-data";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 10;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        if (!isRequestValid(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }
        var status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        val startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
            status = response.getStatus();
        } finally {
            val path = request.getRequestURI();
            if (path.startsWith(NEED_TRACE_PATH_PREFIX) &&
                    !StrUtil.startWithAny(path, IGNORE_TRACE_PATH_PREFIX) &&
                    !Objects.equals(IGNORE_CONTENT_TYPE, request.getContentType())) {
                log.info("Http trace log: {}", mapper.writeValueAsString(HttpTraceLog.builder()
                        .path(path)
                        .method(request.getMethod())
                        .timeTaken(System.currentTimeMillis() - startTime)
                        .time(LocalDateTime.now().toString())
                        .parameterMap(request.getParameterMap())
                        .status(status)
                        .requestBody(getRequestBody(request))
                        .responseBody(getResponseBody(response))
                        .requestHeader(getHeaders(request))
                        .ip(RequestUtils.getIpAddress(request))
                        .build()));
            }
            updateResponse(response);
        }
    }

    private boolean isRequestValid(HttpServletRequest request) {
        try {
            new URI(request.getRequestURL().toString());
            return true;
        } catch (URISyntaxException ex) {
            return false;
        }
    }

    private Map<String, Object> getRequestBody(HttpServletRequest request) {
        var requestBody = "";
        val wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            requestBody = new String(wrapper.getContentAsByteArray());
        }
        try {
            return mapper.readValue(requestBody, new TypeReference<>() {
            });
        } catch (Exception ignore) {
            return null;
        }
    }

    private Map<String, Object> getResponseBody(HttpServletResponse response) {
        var responseBody = "";
        val wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            responseBody = IOUtils.toString(wrapper.getContentInputStream(), StandardCharsets.UTF_8);
        }
        try {
            return mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (Exception ignore) {
            return null;
        }
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        val responseWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        Objects.requireNonNull(responseWrapper).copyBodyToResponse();
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        val wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        var map = new HashMap<String, String>(5);
        if (wrapper != null) {
            val headerNames = wrapper.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                val key = headerNames.nextElement();
                val value = wrapper.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }

    @Data
    @Builder
    private static class HttpTraceLog {
        private String path;
        private Map<String, String[]> parameterMap;
        private String method;
        private Long timeTaken;
        private String time;
        private Integer status;
        private Map<String, String> requestHeader;
        private Map<String, Object> requestBody;
        private Map<String, Object> responseBody;
        private String ip;
    }


}