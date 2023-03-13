package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.manage.dto.sys.log.LogPageDTO;
import com.github.w4o.xx.manage.mapper.SysLogMapper;
import com.github.w4o.xx.manage.param.sys.log.LogPageParam;
import com.github.w4o.xx.manage.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统日志服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysLogServiceImpl implements SysLogService {

    private final SysLogMapper sysLogMapper;

    @Override
    public Page<LogPageDTO> getPageList(LogPageParam param) {
        return sysLogMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
    }

    @Override
    public void clean() {
        sysLogMapper.clean();
    }
}
