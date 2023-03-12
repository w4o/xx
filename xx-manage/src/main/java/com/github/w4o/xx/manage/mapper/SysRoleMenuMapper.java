package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统角色菜单表 Mapper 接口
 *
 * @author Frank
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

    /**
     * 根据用户获取菜单id
     *
     * @param userId 用户id
     * @return 菜单id集合
     */
    @Select("""
            select sys_role_menu.sys_menu_id
            from sys_role_menu,
                 sys_user_role
            where sys_role_menu.deleted = 0
            and sys_role_menu.sys_role_id = sys_user_role.sys_role_id
            and sys_user_role.sys_user_id = #{userId}
            and sys_user_role.deleted = 0
            """)
    List<Long> getMenuIdByUserId(@Param("userId") long userId);
}
