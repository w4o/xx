package com.github.w4o.xx.manage.param.sys.user;

import lombok.Data;

import java.util.Set;

/**
 * 修改用户请求参数
 *
 * @author Frank
 */
@Data
public class ModifyUserParam {

    /**
     * 角色
     */
    private Set<Long> roles;
}
