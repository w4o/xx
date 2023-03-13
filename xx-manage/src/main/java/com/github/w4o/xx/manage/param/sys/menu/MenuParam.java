package com.github.w4o.xx.manage.param.sys.menu;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 菜单请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "菜单参数")
public class MenuParam {
    @NotBlank
    @Schema(title = "路径")
    private String path;

    @NotBlank
    @Schema(title = "菜单名称")
    private String name;

    @NotBlank
    @Schema(title = "vue文件目录")
    private String component;

    @NotNull
    @Schema(title = "父菜单ID", defaultValue = "0", description = "一级菜单为0")
    private Long parentId = NumberUtils.LONG_ZERO;

    @Schema(title = "排序")
    private Integer sort = NumberUtils.INTEGER_ZERO;

    @Schema(title = "重定向到子路由")
    private String redirect;

    @Schema(title = "菜单、面包屑、多标签页显示的名称")
    private String title;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "是否显示在菜单中显示隐藏路由")
    private Boolean hidden;

    @Schema(title = "是否显示在菜单中显示隐藏一级路由")
    private Boolean levelHidden;

}
