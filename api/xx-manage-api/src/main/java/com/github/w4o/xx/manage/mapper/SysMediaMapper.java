package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysMediaEntity;
import com.github.w4o.xx.manage.dto.sys.media.MediaDTO;
import com.github.w4o.xx.manage.param.sys.media.MediaPageParam;
import org.apache.ibatis.annotations.Param;

/**
 * 系统媒体表 Mapper 接口
 *
 * @author Frank
 */
public interface SysMediaMapper extends BaseMapper<SysMediaEntity> {

    /**
     * 分页列表查询
     *
     * @param page 分页参数
     * @return 列表数据
     */
    Page<MediaDTO> findPage(Page<?> page, @Param("condition") MediaPageParam param);
}
