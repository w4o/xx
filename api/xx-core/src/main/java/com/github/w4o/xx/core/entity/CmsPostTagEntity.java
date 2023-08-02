package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 文章标签表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "cms_post_tag")
public class CmsPostTagEntity extends BaseDataEntity {
    /**
     * 文章ID
     */
    private Long postId;
    /**
     * 标签ID
     */
    private Long tagId;
}
