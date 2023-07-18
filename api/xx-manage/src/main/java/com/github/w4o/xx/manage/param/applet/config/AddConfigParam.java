package com.github.w4o.xx.manage.param.applet.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

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
}
