package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role_menu", autoResultMap = true)
public class SysRoleMenuEntity extends BaseDataEntity {

    //gw
    public static LambdaQueryWrapper<SysRoleMenuEntity> gw() {
        return new LambdaQueryWrapper<>();
    }

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
