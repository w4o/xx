package com.github.w4o.xx.manage.param.applet.config;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加小程序配置请求参数")
public class AddConfigParam {
    @NotBlank
    @Schema(description = "配置Key")
    private String configKey;
    @Schema(description = "配置Value")
    private String configValue;
    @Schema(description = "配置描述")
    private String description;
    @Schema(description = "配置类别 1:文本 2:图片")
    private Integer type;
}
