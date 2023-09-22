package com.github.w4o.xx.applet.service.impl;

import com.github.w4o.xx.applet.mapper.AppletBannerMapper;
import com.github.w4o.xx.applet.service.AppletBannerService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.AppletBannerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppletBannerServiceImpl extends BaseServiceImpl<AppletBannerMapper, AppletBannerEntity> implements AppletBannerService {
    @Override
    public Map<Object, List<Map<String, Object>>> getList() {
        List<Map<String, Object>> list = baseMapper.getBannerList();
        return list.stream().collect(Collectors.groupingBy(m -> m.get("position")));
    }
}
