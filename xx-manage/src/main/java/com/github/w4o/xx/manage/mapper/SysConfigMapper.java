package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.mapper.CommonSysConfigMapper;
import com.github.w4o.xx.manage.dto.sys.config.SysConfigPageDTO;
import com.github.w4o.xx.manage.param.sys.config.ConfigPageParam;

/**
 * 系统配置表 Mapper 接口
 *
 * @author Frank
 */
public interface SysConfigMapper extends CommonSysConfigMapper {

    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    Page<SysConfigPageDTO> findPage(Page<?> page, ConfigPageParam param);
}
