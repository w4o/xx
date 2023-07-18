package com.github.w4o.xx.manage.dto.cms.tag;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "文章标签分页数据模型")
public class TagDTO extends BaseDataDTO {
    @Schema(description = "标签ID")
    private Long tagId;
    @Schema(description = "标签名称")
    private String name;
    @Schema(description = "标签描述")
    private String description;
    @Schema(description = "特色图片")
    private String thumbnail;
    @Schema(description = "文章数量")
    private Long postCount;
}
