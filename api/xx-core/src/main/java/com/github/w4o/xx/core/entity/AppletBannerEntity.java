package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
     * 跳转路径
     */
    private String path;
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
    /**
     * 交互类型 1 tabBar 页面
     * 2 保留当前页面，跳转到应用内的某个页面
     * 3 关闭当前页面，跳转到应用内的某个页面
     * 4 打开图片
     */
    private Integer type;
    /**
     * 是否显示
     */
    private Boolean visible;
    /**
     * 位置
     */
    private String position;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
}
