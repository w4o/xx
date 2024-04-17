package com.github.w4o.xx.service.impl;

import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.AppletBannerEntity;
import com.github.w4o.xx.service.mapper.AppletBannerMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Frank
 */
public class AppletBannerService extends BaseServiceImpl<AppletBannerMapper, AppletBannerEntity> implements BaseService<AppletBannerEntity> {
    /**
     * 获取banner列表
     *
     * @return banner列表
     */
    public Map<Object, List<Map<String, Object>>> getList() {
        List<Map<String, Object>> list = baseMapper.getBannerList();
        return list.stream().collect(Collectors.groupingBy(m -> m.get("position")));
    }
}
