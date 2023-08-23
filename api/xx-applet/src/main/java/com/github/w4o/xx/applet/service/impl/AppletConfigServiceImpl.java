package com.github.w4o.xx.applet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.w4o.xx.applet.mapper.AppletConfigMapper;
import com.github.w4o.xx.applet.service.AppletConfigService;
import com.github.w4o.xx.core.entity.AppletConfigEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppletConfigServiceImpl extends ServiceImpl<AppletConfigMapper, AppletConfigEntity> implements AppletConfigService {
    @Override
    @Cacheable(cacheNames = "appletBasicConfig", unless = "#result == null")
    public Map<String, Object> getConfig() {
        List<Map<String, Object>> list = baseMapper.selectMaps(new LambdaQueryWrapper<AppletConfigEntity>()
                .select(AppletConfigEntity::getConfigKey,
                        AppletConfigEntity::getConfigValue));
        if (list != null && !list.isEmpty()) {
            return list.stream().collect(
                    java.util.stream.Collectors.toMap(
                            m -> String.valueOf(m.get("configKey")),
                            m -> m.get("configValue")
                    )
            );
        }
        return new HashMap<>();
    }
}
