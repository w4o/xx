package com.github.w4o.xx.manage.service.impl;

import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysMediaEntity;
import com.github.w4o.xx.manage.mapper.SysMediaMapper;
import com.github.w4o.xx.manage.service.SysMediaService;
import lombok.RequiredArgsConstructor;
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
}
