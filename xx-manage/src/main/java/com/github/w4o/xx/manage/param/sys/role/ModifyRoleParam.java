package com.github.w4o.xx.manage.param.sys.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 修改角色请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "修改角色参数")
public class ModifyRoleParam {

    @NotBlank
    @Schema(description = "角色名", example = "管理员")
    private String name;

    @NotNull
    @Schema(description = "菜单数组", example = "[1,2,3]")
    private Long[] menus = new Long[0];
}
