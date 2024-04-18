package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysLoginLogEntity;
import com.github.w4o.xx.service.dto.sys.log.LoginLogPageDTO;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * 系统登录日志表 Mapper 接口
 *
 * @author Frank
 */
public interface SysLoginLogMapper extends BaseMapper<SysLoginLogEntity> {

    /**
     * 分页查询
     *
     * @param page      分页参数
     * @param condition 查询参数
     * @return 分页数据
     */
    Page<LoginLogPageDTO> findPage(Page<?> page, Map<String, Object> condition);

    /**
     * 清空日志
     */
    @Update("update sys_login_log set deleted = 1 where deleted = 0")
    void clean();
}
