package com.github.w4o.xx.core.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 通用返回类
 *
 * @author Frank
 */
@Data
@Schema(name = "API通用数据")
public class CommonResult<T> {

    public final static int SUCCESS = 0;
    public final static String SUCCESS_MSG = "操作成功";

    @Schema(title = "响应代码", description = "0：表示成功；非0：表示出错")
    private int code;
    
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Schema(title = "业务数据")
    private T data;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Schema(title = "响应描述信息")
    private String msg;

    @Schema(title = "接口版本")
    private String version;

    @Schema(title = "接口执行时间")
    private String timestamp;

    @Schema(title = "接口执行耗时(ms)")
    private long spent;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Schema(title = "跟踪信息", description = "接口调试信息，生产环境无此字段")
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
        return new CommonResult<>(SUCCESS, SUCCESS_MSG, data);
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<>(SUCCESS, SUCCESS_MSG, null);
    }

    public static <T> CommonResult<T> error(CommonError error) {
        return new CommonResult<>(error.getCode(), error.getMessage(), null);
    }

    public static <T> CommonResult<T> error(CommonError error, String trace) {
        return new CommonResult<>(error.getCode(), error.getMessage(), trace);
    }
}
