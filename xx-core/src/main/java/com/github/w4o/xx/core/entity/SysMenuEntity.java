package com.github.w4o.xx.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.w4o.xx.core.base.BaseDataEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 系统菜单表实体
 *
 * @author Frank
 */
@Getter
@Setter
@TableName(value = "sys_menu", autoResultMap = true)
public class SysMenuEntity extends BaseDataEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 第一级路由是为Layout, 其余为层级为vue的文件路径
     */
    private String component;

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

    /**
     * 排序
     */
    private Integer sort;


}
