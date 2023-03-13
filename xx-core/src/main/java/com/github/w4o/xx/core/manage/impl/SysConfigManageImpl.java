package com.github.w4o.xx.core.manage.impl;

import com.github.w4o.xx.core.manage.SysConfigManage;
import com.github.w4o.xx.core.mapper.CommonSysConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 系统字典组件实现
 *
 * @author Frank
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysConfigManageImpl implements SysConfigManage {

    private final CommonSysConfigMapper sysConfigMapper;

    @Override
    @Cacheable(cacheNames = "stringConfigValue", unless = "#result == null")
    public String getStringConfigValue(String label) {
        return sysConfigMapper.getStringValue(label);
    }
}
