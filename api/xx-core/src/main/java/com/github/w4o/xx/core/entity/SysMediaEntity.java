package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 媒体表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName("sys_media")
public class SysMediaEntity extends BaseDataEntity {

    /**
     * 媒体分类ID
     */
    private Long mediaCategoryId;
    /**
     * 媒体标题
     */
    private String title;
    /**
     * 地址
     */
    private String url;
    /**
     * 媒体描述
     */
    private String description;
    /**
     * 媒体类型  1:图片
     */
    private Integer type;

    // 常量信息 ============================================================<<<

    public static int TYPE_IMAGE = 1;
}
