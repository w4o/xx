package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.AppletNavEntity;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.dto.applet.nav.NavDTO;
import com.github.w4o.xx.manage.mapper.AppletNavMapper;
import com.github.w4o.xx.manage.param.applet.nav.NavPageParam;
import com.github.w4o.xx.manage.param.applet.nav.NavParam;
import com.github.w4o.xx.manage.service.AppletNavService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 小程序导航服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppletNavServiceImpl extends BaseServiceImpl<AppletNavMapper, AppletNavEntity> implements AppletNavService {
    @Override
    public Page<NavDTO> getPageList(NavPageParam param) {
        Page<NavDTO> page = baseMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
        handlePageRecord(page);
        return page;
    }

    @Override
    public void add(NavParam param) {
        AppletNavEntity entity = new AppletNavEntity();
        BeanUtils.copyProperties(param, entity);
        baseMapper.insert(entity);
    }

    @Override
    public void update(long id, NavParam param) {
        AppletNavEntity entity = baseMapper.selectById(id);
        AssertUtils.notNull(entity, ErrorCode.E1302);
        BeanUtils.copyProperties(param, entity);
        baseMapper.updateById(entity);
    }

    @Override
    public void delete(long id) {
        baseMapper.deleteById(id);
    }
}
