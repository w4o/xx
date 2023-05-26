package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 系统用户角色表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "sys_user_role", autoResultMap = true)
public class SysUserRoleEntity extends BaseDataEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long sysUserId;

    /**
     * 角色id
     */
    private Long sysRoleId;
}
