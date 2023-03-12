package com.github.w4o.xx.manage.param.sys.config;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改系统配置请求参数
 *
 * @author Frank
 */
@Data
public class ModifyConfigParam {
    @NotBlank
    private String configValue;
    private String remark;
}
