package com.github.w4o.xx.manage.param.sys.config;

import com.github.w4o.xx.core.base.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配置分页查询请求参数
 *
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "配置分页查询参数")
public class ConfigPageParam extends BasePageParam {
}
