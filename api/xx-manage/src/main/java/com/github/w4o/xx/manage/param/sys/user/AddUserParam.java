package com.github.w4o.xx.manage.param.sys.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

/**
 * 添加用户请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "添加用户参数")
public class AddUserParam {
    @NotBlank
    @Schema(description = "用户名", example = "admin")
    private String username;
    @NotBlank
    @Schema(description = "昵称", example = "管理员")
    private String nickName;
    @NotBlank
    @Schema(description = "用户密码", example = "123456")
    private String password;
    @Schema(description = "邮箱", example = "a@b.c")
    private String email;
    @Schema(description = "手机号", example = "13888888888")
    private String mobile;
    @Schema(description = "状态 0：禁用 1：正常", example = "1")
    private Integer status = 1;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "角色(可多个)", example = "[1,2,3]")
    private Set<Long> roles;
    @Schema(description = "部门ID", example = "1")
    private Long deptId;
}