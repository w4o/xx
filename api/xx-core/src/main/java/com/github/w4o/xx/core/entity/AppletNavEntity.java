package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 小程序导航表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "applet_nav")
public class AppletNavEntity extends BaseDataEntity {
    /**
     * 名称
     */
    private String name;
    /**
     * 图标
     */
    private String icon;
    /**
     * 链接/路径
     */
    private String path;
    /**
     * 排序
     */
    private Integer sort;
}
