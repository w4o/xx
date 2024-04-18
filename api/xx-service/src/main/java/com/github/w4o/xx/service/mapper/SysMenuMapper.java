package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单表 Mapper 接口
 *
 * @author Frank
 */
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @Select("select id, parent_id, name, path, component, redirect, hidden, link, level_hidden, title, icon, sort, is_iframe, is_affix, is_keep_alive, create_by, create_time, last_update_by, last_update_time from sys_menu where deleted = 0 order by sort")
    List<Map<String, Object>> selectAll();

    /**
     * 根据用户id查询菜单
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    @Select("""
            select distinct sys_menu.* from sys_menu,
                 sys_role_menu,
                 sys_user_role
            where sys_menu.deleted = 0
            and sys_user_role.deleted = 0
            and sys_role_menu.deleted = 0
            and sys_role_menu.sys_menu_id = sys_menu.id
            and sys_user_role.sys_role_id = sys_role_menu.sys_role_id
            and sys_user_role.sys_user_id = #{userId}
            order by sys_menu.sort
            """)
    List<SysMenuEntity> selectByUserId(@Param("userId") long userId);
}
