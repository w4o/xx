package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysConfigEntity;
import com.github.w4o.xx.core.mapper.CommonSysConfigMapper;
import com.github.w4o.xx.service.dto.sys.config.SysConfigDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置表 Mapper 接口
 *
 * @author Frank
 */
public interface SysConfigMapper extends CommonSysConfigMapper {

    /**
     * 分页查询
     *
     * @param page   分页参数
     * @param entity 查询参数
     * @return 分页数据
     */
    Page<SysConfigDTO> findPage(Page<?> page, @Param("condition") SysConfigEntity entity);
}
