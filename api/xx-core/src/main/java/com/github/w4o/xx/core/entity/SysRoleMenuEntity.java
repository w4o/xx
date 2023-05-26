package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 角色菜单表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "sys_role_menu", autoResultMap = true)
public class SysRoleMenuEntity extends BaseDataEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long sysRoleId;

    /**
     * 菜单id
     */
    private Long sysMenuId;

}
