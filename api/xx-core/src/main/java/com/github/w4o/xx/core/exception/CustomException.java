package com.github.w4o.xx.core.exception;

import com.github.w4o.xx.core.base.CommonError;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常
 *
 * @author Frank
 */
@Getter
@ResponseStatus(HttpStatus.OK)
public class CustomException extends RuntimeException {
    private final CommonError error;
    private String customMsg;

    public CustomException(CommonError error) {
        this.error = error;
    }

    public CustomException(CommonError resultCode, String message) {
        super(message);
        this.error = resultCode;
    }

    public CustomException(CommonError resultCode, Throwable e) {
        super(e);
        this.error = resultCode;
    }

    public CustomException(String msg, ErrorCode code) {
        this.error = code;
        this.customMsg = msg;
    }

    /**
     * 自定义错误信息
     *
     * @param msg 错误信息
     */
    public CustomException(String msg) {
        this.error = ErrorCode.E9999;
        this.customMsg = msg;
    }


}
