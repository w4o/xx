package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "mall_goods_category", autoResultMap = true)
public class MallGoodsCategoryEntity extends BaseDataEntity {

    /**
     * 父分类id
     */
    private Long parentId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类排序
     */
    private Integer sort;
    /**
     * 是否启用
     */
    private Boolean enabled;
}
