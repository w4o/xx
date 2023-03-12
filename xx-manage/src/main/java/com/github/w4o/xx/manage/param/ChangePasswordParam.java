package com.github.w4o.xx.manage.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码请求参数
 *
 * @author Frank
 */
@Data
public class ChangePasswordParam {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
