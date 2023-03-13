package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysLogEntity;
import com.github.w4o.xx.manage.dto.sys.log.LogPageDTO;
import com.github.w4o.xx.manage.param.sys.log.LogPageParam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 系统操作日志表 Mapper 接口
 *
 * @author Frank
 */
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    Page<LogPageDTO> findPage(Page<?> page, @Param("condition") LogPageParam param);

    /**
     * 清空日志
     */
    @Update("update sys_log set deleted = 1 where deleted = 0")
    void clean();

}
