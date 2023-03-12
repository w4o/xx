package com.github.w4o.xx.manage.param.sys.menu;


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

public class MenuParam {
    /**
     * 路径
     */
    @NotBlank
    private String path;

    /**
     * 菜单名称
     */
    @NotBlank
    private String name;

    /**
     * vue文件目录
     */
    @NotBlank
    private String component;

    /**
     * 父菜单ID，一级菜单为0
     */
    @NotNull
    private Long parentId = NumberUtils.LONG_ZERO;

    /**
     * 排序
     */
    private Integer sort = NumberUtils.INTEGER_ZERO;

    /**
     * 重定向到子路由
     */
    private String redirect;

    /**
     * 菜单、面包屑、多标签页显示的名称
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否显示在菜单中显示隐藏路由
     */
    private Boolean hidden;

    /**
     * 是否显示在菜单中显示隐藏一级路由
     */
    private Boolean levelHidden;

}
