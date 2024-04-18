package com.github.w4o.xx.manage.domain.param.sys.role;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * 添加角色请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "添加角色参数")
public class AddRoleParam {

    @NotBlank
    @Schema(description = "角色名", example = "管理员")
    private String roleName;
    @NotBlank
    @Schema(description = "角色标识", example = "admin")
    private String roleCode;
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
