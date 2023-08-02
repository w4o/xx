package com.github.w4o.xx.manage.dto.applet.banner;

import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @Schema(description = "标题")
    private String title;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "排序")
    private Integer sort;
}
