package com.github.w4o.xx.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Builder
public class UploadVO {
    @Schema(description = "文件路径")
    private String filePath;
    @Schema(description = "文件URL")
    private String url;
}
