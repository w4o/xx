package com.github.w4o.xx.core.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 分页结果
 *
 * @author Frank
 */

@Data
@Schema(name = "分页对象", description = "通用返回格式")
public class PageResult<T> {
    @Schema(description = "数据列表")
    private List<T> records;
    @Schema(description = "总数量")
    private Long total;
    @Schema(description = "当前页码")
    private Long current;
    @Schema(description = "是否包含下一页， true:包含 ，false: 不包含")
    private boolean hasNext;
}
