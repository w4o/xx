package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.SysLogEntity;
import org.apache.ibatis.annotations.Update;

/**
 * 系统操作日志表 Mapper 接口
 *
 * @author Frank
 */
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

    /**
     * 清空日志
     */
    @Update("update sys_log set deleted = 1 where deleted = 0")
    void clean();

}
