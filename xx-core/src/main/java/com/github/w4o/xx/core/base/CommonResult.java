package com.github.w4o.xx.core.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 通用返回类
 *
 * @author Frank
 */
@Data
public class CommonResult<T> {

    public final static int SUCCESS = 200;

    @JsonProperty("code")
    private int code;

    @JsonProperty("result")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T data;

    @JsonProperty("msg")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String msg;

    private String version;

    private String timestamp;

    private long spent;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String trace;

    protected CommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    protected CommonResult(int code, String msg, String trac) {
        this.code = code;
        this.msg = msg;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.trace = trac;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(SUCCESS, "操作成功", data);
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<>(SUCCESS, "操作成功", null);
    }

    public static <T> CommonResult<T> error(CommonError error) {
        return new CommonResult<>(error.getCode(), error.getMessage(), null);
    }

    public static <T> CommonResult<T> error(CommonError error, String trace) {
        return new CommonResult<>(error.getCode(), error.getMessage(), trace);
    }
}
