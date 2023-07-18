package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 分类表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "cms_category")
public class CmsCategoryEntity extends BaseDataEntity {
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类描述
     */
    private String description;
    /**
     * 特色图片
     */
    private String thumbnail;
}
