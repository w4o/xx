package com.github.w4o.xx.applet.service;

import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.AppletBannerEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Frank
 */
public interface AppletBannerService extends BaseService<AppletBannerEntity> {
    /**
     * 获取banner列表
     *
     * @return banner列表
     */
    Map<Object, List<Map<String, Object>>> getList();
}
