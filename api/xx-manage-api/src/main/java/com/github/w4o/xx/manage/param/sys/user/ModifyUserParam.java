package com.github.w4o.xx.manage.param.sys.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

/**
 * 修改用户请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "修改用户参数")
public class ModifyUserParam {
    @NotBlank
    @Schema(description = "昵称", example = "管理员")
    private String nickName;
    @Schema(description = "邮箱", example = "a@b.c")
    private String email;
    @Schema(description = "手机号", example = "13888888888")
    private String mobile;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "角色(可多个)", example = "[1,2,3]")
    private Set<Long> roles;
    @Schema(description = "部门ID", example = "1")
    private Long deptId;
}
