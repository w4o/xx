package com.github.w4o.xx.service.dto.cms.post;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import com.github.w4o.xx.service.dto.cms.category.CategoryDTO;
import com.github.w4o.xx.service.dto.cms.tag.TagDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "文章分页数据模型")
public class PostDTO extends BaseDataDTO {
    @Schema(name = "文章ID")
    private Long postId;
    @Schema(name = "文章摘要")
    private String summary;
    @Schema(name = "文章标题")
    private String title;
    @Schema(name = "文章内容")
    private String content;
    @Schema(name = "文章特色图片")
    private String thumbnail;
    @Schema(name = "文章状态")
    private Integer status;
    @Schema(name = "文章状态值")
    private String statusValue;
    @Schema(name = "文章分类")
    private Set<CategoryDTO> categories;
    @Schema(name = "文章标签")
    private Set<TagDTO> tags;
}
