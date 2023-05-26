package com.github.w4o.xx.manage.param.sys.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改系统配置请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "修改配置参数")
public class ModifyConfigParam {
    @NotBlank
    @Schema(description = "配置值", example = "xx")
    private String configValue;
    @Schema(description = "配置备注信息", example = "系统名称")
    private String remark;
}
