package com.github.w4o.xx.manage.param.sys.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * 添加用户请求参数
 *
 * @author Frank
 */
@Data
public class AddUserParam {
    /**
     * 用户名
     */
    @NotBlank
    private String username;

    /**
     * 用户密码
     */
    @NotBlank
    private String password;

    /**
     * 角色
     */
    private Set<Long> roles;
}