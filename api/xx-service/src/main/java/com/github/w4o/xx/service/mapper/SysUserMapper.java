package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysUserEntity;
import com.github.w4o.xx.service.dto.sys.user.UserDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户 Mapper 接口
 *
 * @author Frank
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 分页查询
     *
     * @param page   分页参数
     * @param entity 查询参数
     * @return 分页数据
     */
    Page<UserDTO> findPage(Page<?> page, @Param("condition") SysUserEntity entity);
}
