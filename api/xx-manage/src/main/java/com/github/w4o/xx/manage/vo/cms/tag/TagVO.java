package com.github.w4o.xx.manage.vo.cms.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Builder
@Schema(name = "文章标签模型")
public class TagVO {
    @Schema(description = "标签ID")
    private Long tagId;
    @Schema(description = "标签名称")
    private String name;
    @Schema(description = "标签描述")
    private String description;
    @Schema(description = "特色图片")
    private String thumbnail;
}
