package com.github.w4o.xx.applet.domain.vo.cms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Builder
public class PostDetailVO {
    @Schema(description = "文章ID")
    private Long id;
    @Schema(description = "文章标题")
    private String title;
    @Schema(description = "文章摘要")
    private String summary;
    @Schema(description = "文章内容")
    private String content;
    @Schema(description = "文章缩略图")
    private String thumbnail;
}
