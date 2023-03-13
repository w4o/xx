package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysLoginLogEntity;
import com.github.w4o.xx.core.util.RequestUtils;
import com.github.w4o.xx.manage.dto.sys.log.LoginLogPageDTO;
import com.github.w4o.xx.manage.mapper.SysLoginLogMapper;
import com.github.w4o.xx.manage.param.sys.log.LoginLogPageParam;
import com.github.w4o.xx.manage.service.SysLoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录日志服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysLoginLogServiceImpl implements SysLoginLogService {

    private final SysLoginLogMapper sysLoginLogMapper;

    @Override
    public Page<LoginLogPageDTO> getPageList(LoginLogPageParam param) {
        return sysLoginLogMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
    }

    @Override
    public void clean() {
        sysLoginLogMapper.clean();
    }

    @Override
    public void add(String username) {
        SysLoginLogEntity sysLoginLog = new SysLoginLogEntity();
        sysLoginLog.setIp(RequestUtils.getIpAddress());
        sysLoginLog.setUsername(username);
        sysLoginLog.setUa(RequestUtils.getUserAgent());
        sysLoginLogMapper.insert(sysLoginLog);
    }
}
