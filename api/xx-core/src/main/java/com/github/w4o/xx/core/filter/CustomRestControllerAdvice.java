package com.github.w4o.xx.core.filter;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.github.w4o.xx.core.base.CommonResult;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.BusinessUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 自定义异常处理
 *
 * @author Frank
 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomRestControllerAdvice implements ResponseBodyAdvice<Object> {

    @Value("${app.version}")
    private String version;

    /**
     * 系统出现未捕获的异常时
     */
    @ExceptionHandler(Exception.class)
    public CommonResult<?> exception(Exception e) {
        log.error("an exception occurs", e);
        if (BusinessUtils.isDebug()) {
            return CommonResult.error(ErrorCode.E500, e.getMessage());
        } else {
            return CommonResult.error(ErrorCode.E500);
        }
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, MismatchedInputException.class, HttpMessageNotReadableException.class})
    public CommonResult<?> badRequestException(Exception e) {
        if (BusinessUtils.isDebug()) {
            return CommonResult.error(ErrorCode.E400, e.getMessage());
        } else {
            return CommonResult.error(ErrorCode.E400);
        }
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public CommonResult<?> maxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        if (BusinessUtils.isDebug()) {
            return CommonResult.error(ErrorCode.E9991, e.getMessage());
        } else {
            return CommonResult.error(ErrorCode.E9991);
        }
    }

    /**
     * 自定义业务异常时
     */
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<?> businessException(CustomException e) {
        if (BusinessUtils.isDebug()) {
            return CommonResult.error(e.getError(), e.getMessage());
        } else {
            return CommonResult.error(e.getError());
        }
    }

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NonNull MethodParameter methodParameter,
                                  @NonNull MediaType mediaType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> aClass,
                                  @NonNull ServerHttpRequest serverHttpRequest,
                                  @NonNull ServerHttpResponse serverHttpResponse) {
        if (body instanceof CommonResult<?> commonResult) {
            commonResult.setVersion(version);
            return commonResult;
        }
        return body;
    }

}
