package com.github.w4o.xx.manage.dto.applet.config;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "小程序配置数据模型")
public class ConfigDTO extends BaseDataDTO {
    @Schema(description = "配置ID")
    private Long configId;
    @Schema(description = "配置Key")
    private String configKey;
    @Schema(description = "配置Value")
    private String configValue;
}
