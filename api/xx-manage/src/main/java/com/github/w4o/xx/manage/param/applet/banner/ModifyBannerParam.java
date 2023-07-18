package com.github.w4o.xx.manage.param.applet.banner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Frank
 */
@Data
@Schema(name = "修改小程序轮播图参数")
public class ModifyBannerParam {
    @NotBlank
    @Schema(description = "图片地址")
    private String url;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "排序")
    private Integer sort = 0;
}
