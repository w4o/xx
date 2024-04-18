package com.github.w4o.xx.service.impl.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysLogEntity;
import com.github.w4o.xx.service.dto.sys.log.LogPageDTO;
import com.github.w4o.xx.service.mapper.SysLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 系统日志服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysLogService extends BaseServiceImpl<SysLogMapper, SysLogEntity> implements BaseService<SysLogEntity> {

    private final SysLogMapper sysLogMapper;

    /**
     * 获取分页列表
     *
     * @param page      分页参数
     * @param condition 查询参数
     * @return 分页列表
     */
    public Page<LogPageDTO> getPageList(Page<?> page, Map<String, Object> condition) {
        return sysLogMapper.findPage(page, condition);
    }

    /**
     * 清空日志
     */
    public void clean() {
        sysLogMapper.clean();
    }
}
