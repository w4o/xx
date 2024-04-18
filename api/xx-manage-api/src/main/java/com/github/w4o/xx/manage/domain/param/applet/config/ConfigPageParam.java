package com.github.w4o.xx.manage.domain.param.applet.config;

import com.github.w4o.xx.core.base.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "小程序配置分页查询参数")
public class ConfigPageParam extends BasePageParam {
}
