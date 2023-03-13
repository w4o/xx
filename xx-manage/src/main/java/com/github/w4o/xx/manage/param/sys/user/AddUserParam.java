package com.github.w4o.xx.manage.param.sys.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
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
    @Schema(title = "用户名")
    private String username;

    @NotBlank
    @Schema(title = "用户密码")
    private String password;

    @Schema(title = "角色")
    private Set<Long> roles;
}