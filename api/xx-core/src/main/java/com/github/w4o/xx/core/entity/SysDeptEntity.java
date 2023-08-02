package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 部门表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName("sys_dept")
public class SysDeptEntity extends BaseDataEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 部门名
     */
    private String deptName;

    /**
     * 上级部门ID， 一级部门为0
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否启用
     */
    private Boolean enabled;


}
