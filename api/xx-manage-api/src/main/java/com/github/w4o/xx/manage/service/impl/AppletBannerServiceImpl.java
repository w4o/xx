package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.AppletBannerEntity;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.dto.applet.banner.BannerDTO;
import com.github.w4o.xx.manage.mapper.AppletBannerMapper;
import com.github.w4o.xx.manage.param.applet.banner.BannerPageParam;
import com.github.w4o.xx.manage.param.applet.banner.BannerParam;
import com.github.w4o.xx.manage.service.AppletBannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 小程序轮播图服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppletBannerServiceImpl extends BaseServiceImpl<AppletBannerMapper, AppletBannerEntity> implements AppletBannerService {

    @Override
    public Page<BannerDTO> getPageList(BannerPageParam param) {
        Page<BannerDTO> page = baseMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
        handlePageRecord(page);
        return page;
    }

    @Override
    public void add(BannerParam param) {
        AppletBannerEntity entity = new AppletBannerEntity();
        BeanUtils.copyProperties(param, entity);
        save(entity);
    }

    @Override
    public void update(long id, BannerParam param) {
        AppletBannerEntity entity = baseMapper.selectById(id);
        AssertUtils.notNull(entity);
        BeanUtils.copyProperties(param, entity);
        updateById(entity);
    }

    @Override
    public void delete(long id) {
        removeById(id);
    }

    @Override
    public void updateVisible(long id, boolean visible) {
        AppletBannerEntity entity = baseMapper.selectById(id);
        AssertUtils.notNull(entity);
        entity.setVisible(visible);
        updateById(entity);
    }
}
