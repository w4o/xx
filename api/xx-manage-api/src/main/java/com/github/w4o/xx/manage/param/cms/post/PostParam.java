package com.github.w4o.xx.manage.param.cms.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

import static com.github.w4o.xx.core.entity.CmsPostEntity.STATUS_DRAFT;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加文章参数")
public class PostParam {
    @Schema(description = "文章摘要")
    private String summary;
    @NotBlank
    @Schema(description = "文章标题")
    private String title;
    @Schema(description = "文章内容")
    private String content;
    @Schema(description = "文章特色图片")
    private String thumbnail;
    @Schema(description = "文章状态 1: 草稿 2:已发布")
    private Integer status = STATUS_DRAFT;
    @Schema(description = "文章分类ID")
    private Set<Long> categoryIds;
    @Schema(description = "文章标签")
    private Set<String> tags;
}
