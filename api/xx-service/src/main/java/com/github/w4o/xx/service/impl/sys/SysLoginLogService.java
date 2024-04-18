package com.github.w4o.xx.service.impl.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysLoginLogEntity;
import com.github.w4o.xx.core.util.RequestUtils;
import com.github.w4o.xx.service.dto.sys.log.LoginLogPageDTO;
import com.github.w4o.xx.service.mapper.SysLoginLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 登录日志服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysLoginLogService extends BaseServiceImpl<SysLoginLogMapper, SysLoginLogEntity> implements BaseService<SysLoginLogEntity> {


    /**
     * 获取分页列表
     *
     * @param page      分页参数
     * @param condition 查询参数
     * @return 分页列表
     */
    public Page<LoginLogPageDTO> getPageList(Page<SysLoginLogEntity> page, Map<String, Object> condition) {
        return baseMapper.findPage(page, condition);
    }

    /**
     * 清空日志
     */
    public void clean() {
        baseMapper.clean();
    }

    /**
     * 添加登录日志
     *
     * @param username 用户名
     */
    public void add(String username) {
        SysLoginLogEntity sysLoginLog = new SysLoginLogEntity();
        sysLoginLog.setIp(RequestUtils.getIpAddress());
        sysLoginLog.setUsername(username);
        sysLoginLog.setUa(RequestUtils.getUserAgent());
        baseMapper.insert(sysLoginLog);
    }
}
