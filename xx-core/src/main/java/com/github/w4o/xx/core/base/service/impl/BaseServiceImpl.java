package com.github.w4o.xx.core.base.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.w4o.xx.core.base.dto.BasePageDataDTO;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.mapper.CommonSysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Frank
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Autowired
    private CommonSysUserMapper commonSysUserMapper;

    @Override
    public void handlePageRecord(Page<? extends BasePageDataDTO> page) {
        page.getRecords().forEach(record -> {
            record.setCreateUser(commonSysUserMapper.selectUsernameById(record.getCreateBy()));
            record.setLastUpdateUser(commonSysUserMapper.selectUsernameById(record.getLastUpdateBy()));
        });
    }
}