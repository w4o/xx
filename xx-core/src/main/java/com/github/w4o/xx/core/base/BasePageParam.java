package com.github.w4o.xx.core.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @Schema(title = "页码", description = "默认为1")
    private Long pageNo = 1L;
    @NotNull
    @Max(100)
    @Schema(title = "分页大小", description = "默认为99999")
    private Long pageSize = 99999L;
    /**
     * 排序列
     */
    @Schema(title = "排序列")
    private String sortColumn;
    /**
     * 排序类型
     */
    @Schema(title = "排序类型", description = "asc/desc")
    private String sortType;
}
