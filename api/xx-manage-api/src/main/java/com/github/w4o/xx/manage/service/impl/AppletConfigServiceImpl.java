package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.AppletConfigEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.dto.applet.config.ConfigDTO;
import com.github.w4o.xx.manage.mapper.AppletConfigMapper;
import com.github.w4o.xx.manage.param.applet.config.AddConfigParam;
import com.github.w4o.xx.manage.param.applet.config.ConfigPageParam;
import com.github.w4o.xx.manage.param.applet.config.ModifyConfigParam;
import com.github.w4o.xx.manage.service.AppletConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 小程序配置服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppletConfigServiceImpl extends BaseServiceImpl<AppletConfigMapper, AppletConfigEntity> implements AppletConfigService {

    @Override
    public Page<ConfigDTO> getPageList(ConfigPageParam param) {
        Page<ConfigDTO> page = baseMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
        handlePageRecord(page);
        return page;
    }

    @Override
    public void add(AddConfigParam param) {
        boolean exists = baseMapper.exists(new LambdaQueryWrapper<AppletConfigEntity>()
                .eq(AppletConfigEntity::getConfigKey, param.getConfigKey()));
        if (exists) {
            throw new CustomException(ErrorCode.E1300);
        }
        AppletConfigEntity entity = new AppletConfigEntity();
        BeanUtils.copyProperties(param, entity);
        baseMapper.insert(entity);
    }

    @Override
    public void update(long id, ModifyConfigParam param) {
        AppletConfigEntity entity = baseMapper.selectById(id);
        AssertUtils.notNull(entity, ErrorCode.E1301);
        BeanUtils.copyProperties(param, entity);
        baseMapper.updateById(entity);
    }

    @Override
    public void delete(long id) {
        baseMapper.deleteById(id);
    }
}
