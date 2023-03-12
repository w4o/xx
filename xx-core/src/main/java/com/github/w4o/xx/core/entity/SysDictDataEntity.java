package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 系统字典表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName("sys_dict_data")
public class SysDictDataEntity extends BaseDataEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 字典类型表id
     */
    private Long sysDictTypeId;
    /**
     * 数据值
     */
    private String value;

    /**
     * 标签名
     */
    private String label;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    // 常量信息 ============================================================<<<

}
