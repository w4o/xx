package com.github.w4o.xx.manage.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "修改密码参数")
public class ChangePasswordParam {
    @NotBlank
    @Schema(description = "旧密码（做AES加密）", example = "KiFfbShnMS0ISIwPFEPk+g==")
    private String oldPassword;
    @NotBlank
    @Schema(description = "新密码（做AES加密）", example = "KiFfbShnMS0ISIwPFEPk+g==")
    private String newPassword;
}
