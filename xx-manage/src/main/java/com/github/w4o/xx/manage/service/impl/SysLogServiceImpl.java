package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysLogEntity;
import com.github.w4o.xx.manage.mapper.SysLogMapper;
import com.github.w4o.xx.manage.param.sys.log.LogPageParam;
import com.github.w4o.xx.manage.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 系统日志服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysLogServiceImpl implements SysLogService {

    private final SysLogMapper sysLogMapper;

    @Override
    public Page<SysLogEntity> getPageList(LogPageParam param) {
        var queryWrapper = new LambdaQueryWrapper<SysLogEntity>()
                .orderByDesc(SysLogEntity::getCreateTime);
        if (StringUtils.isNotEmpty(param.getUsername())) {
            queryWrapper.like(SysLogEntity::getUserName, param.getUsername());
        }

        if (StringUtils.isNotEmpty(param.getStartDate())) {
            queryWrapper.ge(SysLogEntity::getCreateTime, LocalDateTime.parse(param.getStartDate() + " 00:00:00", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss")));
        }

        if (StringUtils.isNotEmpty(param.getEndDate())) {
            queryWrapper.le(SysLogEntity::getCreateTime, LocalDateTime.parse(param.getEndDate() + " 23:59:59", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss")));
        }
        return sysLogMapper.selectPage(new Page<>(param.getPageNo(), param.getPageSize()), queryWrapper);
    }

    @Override
    public void clean() {
        sysLogMapper.clean();
    }
}
