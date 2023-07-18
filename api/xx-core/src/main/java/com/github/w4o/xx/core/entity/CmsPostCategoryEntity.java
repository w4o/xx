package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 文章分类表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "cms_post_category")
public class CmsPostCategoryEntity extends BaseDataEntity {
    /**
     * 文章ID
     */
    private Long postId;
    /**
     * 分类ID
     */
    private Long categoryId;
}
