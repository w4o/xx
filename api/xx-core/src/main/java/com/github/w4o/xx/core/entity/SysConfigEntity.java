package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 系统配置表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName("sys_config")
@EqualsAndHashCode(callSuper = true)
public class SysConfigEntity extends BaseDataEntity {

    //gw
    public static LambdaQueryWrapper<SysConfigEntity> gw() {
        return new LambdaQueryWrapper<>();
    }

    @Serial
    private static final long serialVersionUID = 1L;

    private String configKey;
    private String configValue;
    private String remark;
}
