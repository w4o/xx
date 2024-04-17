package com.github.w4o.xx.manage.dto.applet.banner;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Frank
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "小程序轮播图数据模型")
public class BannerDTO extends BaseDataDTO {
    @Schema(description = "轮播图ID")
    private String bannerId;
    @Schema(description = "图片地址")
    private String url;
    @Schema(description = "跳转路径")
    private String path;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "排序")
    private Integer sort;
    @Schema(description = "是否显示")
    private Boolean visible;
    @Schema(description = "交互类型 1 tabBar 页面 2 保留当前页面，跳转到应用内的某个页面 3 关闭当前页面，跳转到应用内的某个页面 4 打开放大")
    private Integer type;
    @Schema(description = "位置")
    private String position;
    @Schema(description = "开始时间")
    private LocalDateTime startTime;
    @Schema(description = "结束时间")
    private LocalDateTime endTime;
}
