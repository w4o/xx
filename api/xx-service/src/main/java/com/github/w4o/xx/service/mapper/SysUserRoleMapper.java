package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.SysUserRoleEntity;
import com.github.w4o.xx.service.dto.sys.role.UserRoleDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户角色表 Mapper 接口
 *
 * @author Frank
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

    /**
     * 根据用户id查询角色信息
     *
     * @param userId 用户id
     * @return 角色信息
     */
    @Select("select sys_role.id as role_id, sys_role.role_name from sys_role, sys_user_role where sys_user_role.deleted =0 and sys_role.id = sys_user_role.sys_role_id and sys_user_role.sys_user_id = #{userId} ")
    List<UserRoleDTO> findRoleByUserId(@Param("userId") long userId);

    /**
     * 根据用户id查询角色id
     *
     * @param userId 用户id
     * @return 角色id
     */
    @Select("select sys_role_id from sys_user_role where deleted =0 and sys_user_id = #{userId} ")
    List<Long> findRoleIdByUserId(@Param("userId") long userId);
}
