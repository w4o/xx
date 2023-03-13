package com.github.w4o.xx.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Frank
 */
public interface CommonSysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 根据id查用户名
     *
     * @param id 主键id
     * @return 用户名
     */
    @Select("select username from sys_user where id = #{id}")
    String selectUsernameById(@Param("id") Long id);
}
