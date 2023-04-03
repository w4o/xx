package com.github.w4o.xx.core.base.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.w4o.xx.core.base.dto.BaseDataDTO;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.mapper.CommonSysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Frank
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Autowired
    private CommonSysUserMapper commonSysUserMapper;

    @Override
    public void handlePageRecord(Page<? extends BaseDataDTO> page) {
        page.getRecords().forEach(this::handleOneData);
    }

    @Override
    public void handleListData(List<? extends BaseDataDTO> list) {
        list.forEach(this::handleOneData);
    }

    @Override
    public void handleOneData(BaseDataDTO data) {
        if (data.getCreateBy() != null) {
            data.setCreateUser(commonSysUserMapper.selectUsernameById(data.getCreateBy()));
        }
        if (data.getLastUpdateBy() != null) {
            data.setLastUpdateUser(commonSysUserMapper.selectUsernameById(data.getLastUpdateBy()));
        }
    }
}
