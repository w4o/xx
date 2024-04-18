package com.github.w4o.xx.manage.domain.param.sys.menu;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 菜单请求参数
 *
 * @author Frank
 */
@Data
@Schema(name = "菜单参数")
public class MenuParam {
    @NotNull
    @Schema(description = "父菜单ID", defaultValue = "0", example = "0")
    private Long parentId = NumberUtils.LONG_ZERO;
    @NotBlank
    @Schema(description = "菜单名称", example = "菜单管理")
    private String name;
    @NotBlank
    @Schema(description = "路径", example = "/sys/menu")
    private String path;
    @NotBlank
    @Schema(description = "vue文件目录", example = "sys/menu")
    private String component;
    @Schema(description = "重定向到子路由", example = "noRedirect")
    private String redirect;
    @NotBlank
    @Schema(description = "菜单、面包屑、多标签页显示的名称", example = "菜单管理")
    private String title;
    @Schema(description = "图标", example = "el-icon-s-help")
    private String icon;
    @Schema(description = "是否显示在菜单中显示隐藏路由", example = "true")
    private Boolean isHide;
    @Schema(description = "菜单外链链接", example = "http://xxxxx.xx")
    private String link;
    @Schema(description = "是否为外链", example = "true")
    private Boolean isLink = false;
    @Schema(description = "排序", defaultValue = "0", example = "0")
    private Integer sort = NumberUtils.INTEGER_ZERO;
    @Schema(description = "是否为外链", example = "true")
    private Boolean isIframe;
    @Schema(description = "菜单是否固定", example = "true")
    private Boolean isAffix;
    @Schema(description = "菜单是否缓存", example = "true")
    private Boolean isKeepAlive;
}
