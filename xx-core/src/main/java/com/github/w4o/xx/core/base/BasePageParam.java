package com.github.w4o.xx.core.base;

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
public class BasePageParam {
    @NotNull
    @Min(1)
    private Long pageNo = 1L;
    @NotNull
    @Max(100)
    private Long pageSize = 99999L;

    /**
     * 排序列
     */
    private String sortColumn;
    /**
     * 排序类型
     */
    private String sortType;
}
