package com.github.w4o.xx.core.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 基础分页查询参数类
 *
 * @author Frank
 */
@Data
@Schema(name = "通用分页查询参数")
public class BasePageParam {
    @NotNull
    @Min(1)
    @Schema(description = "页码", example = "1")
    private Long pageNo = 1L;
    @NotNull
    @Max(100)
    @Schema(description = "分页大小", example = "20")
    private Long pageSize = 99999L;
    /**
     * 排序列
     */
    @Schema(description = "排序列")
    private String sortColumn;
    /**
     * 排序类型
     */
    @Schema(description = "排序类型")
    private String sortType;
}
