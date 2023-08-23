package com.github.w4o.xx.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.w4o.xx.core.entity.AppletConfigEntity;

import java.util.Map;

/**
 * @author Frank
 */
public interface AppletConfigService extends IService<AppletConfigEntity> {
    /**
     * 获取小程序配置信息
     *
     * @return 小程序配置信息
     */
    Map<String, Object> getConfig();
}
