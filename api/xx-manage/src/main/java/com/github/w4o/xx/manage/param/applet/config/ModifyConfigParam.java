package com.github.w4o.xx.manage.param.applet.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Schema(name = "修改小程序配置参数")
public class ModifyConfigParam {
    @Schema(description = "配置Value")
    private String configValue;
    @Schema(description = "配置描述")
    private String description;
}
