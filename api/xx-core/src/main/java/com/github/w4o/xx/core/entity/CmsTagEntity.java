package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 标签表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "cms_tag")
public class CmsTagEntity extends BaseDataEntity {
    /**
     * 标签名称
     */
    private String name;
    /**
     * 标签描述
     */
    private String description;
    /**
     * 特色图片
     */
    private String thumbnail;
}
