package com.github.w4o.xx.manage.param.sys.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 添加配置请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "添加配置参数")
public class AddConfigParam {
    @NotBlank
    @Schema(description = "配置key", example = "system.name")
    private String configKey;
    @NotBlank
    @Schema(description = "配置值", example = "xx")
    private String configValue;
    @Schema(description = "配置备注信息", example = "系统名称")
    private String remark;
}
