package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysMediaEntity;
import com.github.w4o.xx.service.dto.sys.media.MediaDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 系统媒体表 Mapper 接口
 *
 * @author Frank
 */
public interface SysMediaMapper extends BaseMapper<SysMediaEntity> {

    /**
     * 分页列表查询
     *
     * @param page      分页参数
     * @param condition 查询条件
     * @return 列表数据
     */
    Page<MediaDTO> findPage(Page<?> page, @Param("condition") Map<String, Object> condition);
}
