package com.github.w4o.xx.manage.param.sys.media.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加或修改媒体分类参数")
public class MediaCategoryParam {
    @NotNull
    private Long parentId = 0L;
    @NotBlank
    private String name;
}
