package com.github.w4o.xx.manage.domain.vo.cms.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Builder
@Schema(name = "文章分类模型")
public class CategoryVO {
    @Schema(description = "分类ID")
    private Long categoryId;
    @Schema(description = "分类名称")
    private String name;
    @Schema(description = "分类描述")
    private String description;
    @Schema(description = "特色图片")
    private String thumbnail;
}
