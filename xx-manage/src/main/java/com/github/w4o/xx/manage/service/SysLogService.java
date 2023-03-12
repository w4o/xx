package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysLogEntity;
import com.github.w4o.xx.manage.param.sys.log.LogPageParam;

/**
 * 系统日志服务接口
 *
 * @author Frank
 */
public interface SysLogService {

    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<SysLogEntity> getPageList(LogPageParam param);

    /**
     * 清空日志
     */
    void clean();

}
