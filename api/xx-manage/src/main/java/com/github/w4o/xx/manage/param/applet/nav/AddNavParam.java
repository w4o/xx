package com.github.w4o.xx.manage.param.applet.nav;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Frank
 */
@Data
@Schema(name = "添加小程序导航请求参数")
public class AddNavParam {
    @NotBlank
    @Schema(description = "名称")
    private String name;
    @NotBlank
    @Schema(description = "图标")
    private String icon;
    @Schema(description = "链接/路径")
    private String path;
    @Schema(description = "排序")
    private Integer sort = 0;
}
