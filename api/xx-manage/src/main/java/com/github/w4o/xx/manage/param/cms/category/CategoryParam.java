package com.github.w4o.xx.manage.param.cms.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加或修改文章分类参数")
public class CategoryParam {
    @NotNull
    private Long parentId = 0L;
    @NotBlank
    @Schema(description = "分类名称")
    private String name;
    @Schema(description = "分类描述")
    private String description;
    @Schema(description = "特色图片")
    private String thumbnail;
}
