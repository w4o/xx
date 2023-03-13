package com.github.w4o.xx.manage.param.sys.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 添加配置请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "添加配置参数")
public class AddConfigParam {
    @NotBlank
    @Schema(title = "配置key")
    private String configKey;
    @NotBlank
    @Schema(title = "配置值")
    private String configValue;
    @Schema(title = "配置备注信息")
    private String remark;
}
