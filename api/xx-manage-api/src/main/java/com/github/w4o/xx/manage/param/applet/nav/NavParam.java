package com.github.w4o.xx.manage.param.applet.nav;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加或修改小程序导航请求参数")
public class NavParam {
    @NotBlank
    @Schema(description = "名称")
    private String name;
    @NotBlank
    @Schema(description = "图标")
    private String icon;
    @Schema(description = "链接/路径")
    private String path;
    @Schema(description = "分享图")
    private String shareImage;
    @Schema(description = "排序")
    private Integer sort = 0;
}
