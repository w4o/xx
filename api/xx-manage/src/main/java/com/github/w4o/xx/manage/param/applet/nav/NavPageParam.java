package com.github.w4o.xx.manage.param.applet.nav;

import com.github.w4o.xx.core.base.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "小程序导航分页查询参数")
public class NavPageParam extends BasePageParam {
}
