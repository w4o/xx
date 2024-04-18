package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.AppletNavEntity;
import com.github.w4o.xx.service.dto.applet.nav.NavDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 小程序导航表 Mapper 接口
 *
 * @author Frank
 */
public interface AppletNavMapper extends BaseMapper<AppletNavEntity> {
    /**
     * 分页查询
     *
     * @param page   分页参数
     * @param entity 查询参数
     * @return 分页数据
     */
    @Select("""
            select id as navId,
            name,
            icon,
            path,
            sort,
            create_by,
            create_time,
            last_update_by,
            last_update_time
            from applet_nav
            where deleted = 0
            """)
    Page<NavDTO> findPage(Page<AppletNavEntity> page, @Param("condition") AppletNavEntity entity);
}
