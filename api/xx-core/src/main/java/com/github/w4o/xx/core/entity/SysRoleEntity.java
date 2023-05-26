package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 系统角色表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "sys_role", autoResultMap = true)
public class SysRoleEntity extends BaseDataEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色标识
     */
    private String roleCode;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否启用
     */
    private Boolean enabled;
    /**
     * 排序
     */
    private Integer sort;
}
