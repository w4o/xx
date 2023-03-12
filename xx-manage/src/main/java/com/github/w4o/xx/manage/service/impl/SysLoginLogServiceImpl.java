package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysLoginLogEntity;
import com.github.w4o.xx.core.util.RequestUtils;
import com.github.w4o.xx.manage.mapper.SysLoginLogMapper;
import com.github.w4o.xx.manage.service.SysLoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public Page<Map<String, Object>> getPageList(long pageNo, long pageSize) {
        return sysLoginLogMapper.selectMapsPage(new Page<>(pageNo, pageSize),
                new LambdaQueryWrapper<SysLoginLogEntity>()
                        .eq(SysLoginLogEntity::getDeleted, false)
                        .select(SysLoginLogEntity::getId,
                                SysLoginLogEntity::getUserName,
                                SysLoginLogEntity::getIp,
                                SysLoginLogEntity::getCreateTime,
                                SysLoginLogEntity::getStatus)
        );
    }

    @Override
    public void clean() {
        sysLoginLogMapper.clean();
    }

    @Override
    public void add(String username) {
        SysLoginLogEntity sysLoginLog = new SysLoginLogEntity();
        sysLoginLog.setIp(RequestUtils.getIpAddress());
        sysLoginLog.setUserName(username);
        sysLoginLogMapper.insert(sysLoginLog);
    }
}
