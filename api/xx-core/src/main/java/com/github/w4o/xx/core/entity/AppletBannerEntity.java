package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 小程序轮播图表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "applet_banner")
public class AppletBannerEntity extends BaseDataEntity {
    /**
     * 图片地址
     */
    private String url;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
}
