package com.github.w4o.xx.manage.param.cms.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Frank
 */
@Data
@Schema(name = "修改文章分类参数")
public class ModifyCategoryParam {
    @NotBlank
    @Schema(description = "分类名称")
    private String name;
    @Schema(description = "分类描述")
    private String description;
    @Schema(description = "特色图片")
    private String thumbnail;
}
