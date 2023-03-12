package com.github.w4o.xx.manage.param.sys.role;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加角色菜单请求参数
 *
 * @author Frank
 */
@Data
public class AddRoleMenuParam {

    /**
     * 角色ID
     */
    @NotNull
    private Long roleId;

    /**
     * 菜单ID
     */
    @NotNull
    private Long[] menuIds;
}
