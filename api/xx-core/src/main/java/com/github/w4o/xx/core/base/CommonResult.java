package com.github.w4o.xx.core.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
@Schema(name = "API通用数据", description = "通用返回格式")
public class CommonResult<T> {

    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "操作成功";

    @Schema(description = "响应代码")
    private int code;

    @Schema(description = "接口是否成功")
    private boolean success;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Schema(description = "业务数据")
    private T data;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Schema(description = "响应描述信息")
    private String msg;

    @Schema(description = "接口版本")
    private String version;

    @Schema(description = "接口执行时间")
    private String timestamp;

    @Schema(description = "接口执行耗时(ms)")
    private long spent;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Schema(description = "跟踪信息")
    private String trace;

    protected CommonResult(boolean success, int code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    protected CommonResult(boolean success, int code, String msg, String trac) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.trace = trac;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(true, SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<>(true, SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public static <T> CommonResult<PageResult<T>> page(IPage<T> iPage) {
        PageResult<T> result = new PageResult<>();
        // 记录明细
        result.setRecords(iPage.getRecords());
        // 总条数
        result.setTotal(iPage.getTotal());
        // 当前页码
        result.setCurrent(iPage.getCurrent());
        // 是否有下一页
        result.setHasNext(iPage.getPages() > iPage.getCurrent());
        return success(result);
    }

    public static <T> CommonResult<T> error(CommonError error) {
        return new CommonResult<>(false, error.getCode(), error.getMessage(), null);
    }

    public static <T> CommonResult<T> error(CommonError error, String trace) {
        return new CommonResult<>(false, error.getCode(), error.getMessage(), trace);
    }
}
