package com.github.w4o.xx.manage.param;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 登陆请求参数
 *
 * @author Frank
 */
@Data

public class LoginParam {
    @NotBlank
    @Length(max = 50)
    private String username;
    @NotBlank
    private String password;

    private String captchaKey;

    @NotBlank
    private String verificationCode;
}
