package com.github.w4o.xx.core.manage.impl;

import com.github.w4o.xx.core.entity.SysDictDataEntity;
import com.github.w4o.xx.core.manage.SysDictManage;
import com.github.w4o.xx.core.mapper.CommonSysDictDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 系统字典组件实现
 *
 * @author Frank
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictManageImpl implements SysDictManage {

    private final CommonSysDictDataMapper commonSysDictDataMapper;

    @Override
    @Cacheable(cacheNames = "sysDictMap", unless = "#result == null")
    public List<Map<String, Object>> getDictMapByLabel(String label) {
        return commonSysDictDataMapper.getByLabel(label);
    }

    @Override
    @Cacheable(cacheNames = "sysDictById", unless = "#result == null")
    public SysDictDataEntity getDictById(Long id) {
        return commonSysDictDataMapper.selectById(id);
    }

    @Override
    @Cacheable(cacheNames = "sysDictValueById", unless = "#result == null")
    public String getDictValueById(Long id) {
        SysDictDataEntity sysDictEntity = this.getDictById(id);
        if (sysDictEntity == null) {
            return null;
        } else {
            return sysDictEntity.getValue();
        }
    }
}
