package com.github.w4o.xx.manage.domain.param;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

/**
 * 登录请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "系统登录参数")
public class LoginParam {
    @NotBlank
    @Length(max = 50)
    @Schema(description = "用户名", example = "admin")
    private String username;
    @NotBlank
    @Schema(description = "密码（做AES加密）", example = "KiFfbShnMS0ISIwPFEPk+g==")
    private String password;
    @Schema(description = "验证码Key", example = "b3d0b0b0-0b0b-0b0b-0b0b-0b0b0b0b0b0b")
    private String captchaKey;
    @NotBlank
    @Schema(description = "验证码", example = "123456")
    private String verificationCode;
}
