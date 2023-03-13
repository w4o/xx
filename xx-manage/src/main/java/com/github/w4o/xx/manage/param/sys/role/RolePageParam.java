package com.github.w4o.xx.manage.param.sys.role;

import com.github.w4o.xx.core.base.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "角色分页查询参数")
public class RolePageParam extends BasePageParam {
}
