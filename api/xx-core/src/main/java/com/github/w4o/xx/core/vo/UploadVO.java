package com.github.w4o.xx.core.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Builder
public class UploadVO {
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件URL
     */
    private String url;
}
