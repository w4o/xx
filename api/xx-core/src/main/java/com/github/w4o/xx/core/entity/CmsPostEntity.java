package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 文章表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "cms_post")
public class CmsPostEntity extends BaseDataEntity {
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章特色图片
     */
    private String thumbnail;
    /**
     * 文章状态 1: 草稿 2:已发布
     */
    private Integer status;

    // 常量信息 ============================================================<<<

    /**
     * 文章状态 1: 草稿
     */
    public static final int STATUS_DRAFT = 1;
    /**
     * 文章状态 2:已发布
     */
    public static final int STATUS_PUBLISH = 2;

    public static final Map<Integer, String> STATUS_MAP = Map.of(1, "草稿", 2, "已发布");
}
