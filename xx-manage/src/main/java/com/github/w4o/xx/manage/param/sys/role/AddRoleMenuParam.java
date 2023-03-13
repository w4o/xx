package com.github.w4o.xx.manage.param.sys.role;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加角色菜单请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "添加角色菜单参数")
public class AddRoleMenuParam {

    @NotNull
    @Schema(title = "角色ID")
    private Long roleId;

    @NotNull
    @Schema(title = "菜单ID")
    private Long[] menuIds;
}
