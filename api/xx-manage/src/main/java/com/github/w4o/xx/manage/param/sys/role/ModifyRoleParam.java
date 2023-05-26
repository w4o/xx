package com.github.w4o.xx.manage.param.sys.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

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
    private String roleName;
    @NotNull
    @Schema(description = "排序", example = "1")
    private Integer sort = 0;
    @Schema(description = "角色描述", example = "管理员")
    private String description;
    @NotNull
    @Schema(description = "是否启用", example = "true")
    private Boolean enabled = true;
    @NotNull
    @Schema(description = "菜单数组", example = "[1,2,3]")
    private Set<Long> menus = new HashSet<>();
}
