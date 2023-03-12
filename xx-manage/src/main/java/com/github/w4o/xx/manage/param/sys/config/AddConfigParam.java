package com.github.w4o.xx.manage.param.sys.config;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 添加配置请求参数
 *
 * @author Frank
 */
@Data
public class AddConfigParam {
    @NotBlank
    private String configKey;
    @NotBlank
    private String configValue;
    private String remark;
}
