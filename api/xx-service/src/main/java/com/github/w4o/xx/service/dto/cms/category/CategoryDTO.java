package com.github.w4o.xx.service.dto.cms.category;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "文章分类分页数据模型")
public class CategoryDTO extends BaseDataDTO {
    @Schema(description = "分类ID")
    private Long categoryId;
    @Schema(description = "分类名称")
    private String name;
    @Schema(description = "分类描述")
    private String description;
    @Schema(description = "特色图片")
    private String thumbnail;
    @Schema(description = "文章数量")
    private Long postCount;
}
