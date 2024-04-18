package com.github.w4o.xx.manage.domain.param.sys.role;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 添加角色菜单请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "添加角色菜单参数")
public class AddRoleMenuParam {

    @NotNull
    @Schema(description = "角色ID", example = "1")
    private Long roleId;

    @NotNull
    @Schema(description = "菜单ID", example = "[1,2,3]")
    private Long[] menuIds;
}
