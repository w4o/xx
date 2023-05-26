package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 系统字典类型表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName("sys_dict_type")
public class SysDictTypeEntity extends BaseDataEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 标签名
     */
    private String label;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 备注
     */
    private String remark;


}
