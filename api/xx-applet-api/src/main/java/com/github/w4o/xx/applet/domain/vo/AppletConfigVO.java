package com.github.w4o.xx.applet.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Frank
 */
@Data
@Builder
public class AppletConfigVO {
    @Schema(description = "基础配置")
    private Map<String, Object> basic;
    @Schema(description = "banner配置")
    private Map<Object, List<Map<String, Object>>> banner;
}
