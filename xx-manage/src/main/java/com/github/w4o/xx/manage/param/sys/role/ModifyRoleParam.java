package com.github.w4o.xx.manage.param.sys.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加角色请求参数
 *
 * @author Frank
 */
@Data
public class ModifyRoleParam {

    /**
     * 角色名
     */
    @NotBlank
    private String name;

    /**
     * 菜单数组
     */
    @NotNull
    private Long[] menus = new Long[0];
}
