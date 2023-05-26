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
    @Schema(description = "用户名", example = "admin")
    private String username;

    @NotBlank
    @Schema(description = "用户密码（做AES加密）", example = "KiFfbShnMS0ISIwPFEPk+g==")
    private String password;

    @Schema(description = "角色", example = "[1,2,3]")
    private Set<Long> roles;
}