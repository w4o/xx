package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 媒体分类表实体类
 *
 * @author Frank
 */
@Getter
@Setter
@TableName("sys_media_category")
public class SysMediaCategoryEntity extends BaseDataEntity {
    private String name;
    private Long parentId;
}
