package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.AppletConfigEntity;
import com.github.w4o.xx.manage.dto.applet.config.ConfigDTO;
import com.github.w4o.xx.manage.param.applet.config.ConfigPageParam;
import org.apache.ibatis.annotations.Select;

/**
 * 小程序配置表 Mapper 接口
 *
 * @author Frank
 */
public interface AppletConfigMapper extends BaseMapper<AppletConfigEntity> {
    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    @Select("""
            select id as configId,
            config_key,
            config_value,
            create_by,
            create_time,
            last_update_by,
            last_update_time
            from applet_config
            where deleted = 0
            """)
    Page<ConfigDTO> findPage(Page<?> page, ConfigPageParam param);
}
