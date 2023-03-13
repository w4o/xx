package com.github.w4o.xx.manage.param;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 登陆请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "系统登陆参数")
public class LoginParam {
    @NotBlank
    @Length(max = 50)
    @Schema(description = "用户名")
    private String username;
    @NotBlank
    @Schema(description = "密码")
    private String password;
    @Schema(description = "验证码Key")
    private String captchaKey;
    @NotBlank
    @Schema(description = "验证码")
    private String verificationCode;
}
