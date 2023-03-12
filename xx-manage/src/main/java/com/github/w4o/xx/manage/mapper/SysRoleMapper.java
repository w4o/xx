package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysRoleEntity;

import java.util.Map;

/**
 * 系统角色表 Mapper 接口
 *
 * @author Frank
 */
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    /**
     * 分页列表查询
     *
     * @param page 分页参数
     * @return 列表数据
     */
    Page<Map<String, Object>> findPage(Page<?> page);
}
