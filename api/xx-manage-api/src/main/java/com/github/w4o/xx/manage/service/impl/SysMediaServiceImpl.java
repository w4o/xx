package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysMediaEntity;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.dto.sys.media.MediaDTO;
import com.github.w4o.xx.manage.mapper.SysMediaMapper;
import com.github.w4o.xx.manage.param.sys.media.MediaPageParam;
import com.github.w4o.xx.manage.param.sys.media.MediaParam;
import com.github.w4o.xx.manage.service.SysMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统媒体管理服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMediaServiceImpl extends BaseServiceImpl<SysMediaMapper, SysMediaEntity> implements SysMediaService {

    @Override
    public Page<MediaDTO> getPageList(MediaPageParam param) {
        Page<MediaDTO> page = baseMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
        handlePageRecord(page);
        return page;
    }

    @Override
    public void update(Long id, MediaParam param) {
        SysMediaEntity entity = baseMapper.selectById(id);
        AssertUtils.notNull(entity, ErrorCode.E1001);
        BeanUtils.copyProperties(param, entity);
        baseMapper.updateById(entity);
    }
}
