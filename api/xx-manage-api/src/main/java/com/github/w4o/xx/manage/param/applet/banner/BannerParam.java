package com.github.w4o.xx.manage.param.applet.banner;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加或修改小程序轮播图请求参数")
public class BannerParam {
    @NotBlank
    @Schema(description = "图片地址")
    private String url;
    @Schema(description = "跳转路径")
    private String path;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "排序")
    private Integer sort = 0;
    @Schema(description = "交互类型 1 tabBar 页面 2 保留当前页面，跳转到应用内的某个页面 3 关闭当前页面，跳转到应用内的某个页面 4 打开放大")
    private Integer type = 1;
    @Schema(description = "是否显示")
    private Boolean visible = true;
    @Schema(description = "位置")
    private String position;
    @Schema(description = "开始时间")
    private LocalDateTime startTime;
    @Schema(description = "结束时间")
    private LocalDateTime endTime;
}
