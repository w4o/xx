package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysLogEntity;
import com.github.w4o.xx.service.dto.sys.log.LogPageDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * 系统操作日志表 Mapper 接口
 *
 * @author Frank
 */
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

    /**
     * 分页查询
     *
     * @param page      分页参数
     * @param condition 查询参数
     * @return 分页数据
     */
    Page<LogPageDTO> findPage(Page<?> page, @Param("condition") Map<String, Object> condition);

    /**
     * 清空日志
     */
    @Update("update sys_log set deleted = 1 where deleted = 0")
    void clean();

}
