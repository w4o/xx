package com.github.w4o.xx.manage.domain.param.sys.media;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Frank
 */
@Data
public class MediaParam {
    @Schema(description = "媒体分类ID")
    private Long mediaCategoryId;
    @Schema(description = "媒体标题")
    private String title;
    @Schema(description = "媒体描述")
    private String description;
}
