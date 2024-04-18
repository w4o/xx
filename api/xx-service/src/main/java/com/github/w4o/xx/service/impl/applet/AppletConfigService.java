package com.github.w4o.xx.service.impl.applet;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.AppletConfigEntity;
import com.github.w4o.xx.service.dto.applet.config.ConfigDTO;
import com.github.w4o.xx.service.mapper.AppletConfigMapper;
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
public class AppletConfigService extends BaseServiceImpl<AppletConfigMapper, AppletConfigEntity> implements BaseService<AppletConfigEntity> {
    /**
     * 获取小程序配置信息
     *
     * @return 小程序配置信息
     */
    @Cacheable(cacheNames = "appletBasicConfig", unless = "#result == null")
    public Map<String, Object> getConfig() {
        List<Map<String, Object>> list = baseMapper.selectMaps(new LambdaQueryWrapper<AppletConfigEntity>()
                .select(AppletConfigEntity::getConfigKey, AppletConfigEntity::getConfigValue));
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

    /**
     * 获取分页列表
     *
     * @param entity 请求参数
     * @return 分页列表
     */
    public Page<ConfigDTO> getPageList(Page<AppletConfigEntity> page, AppletConfigEntity entity) {
        Page<ConfigDTO> pageList = baseMapper.findPage(page, entity);
        handlePageRecord(pageList);
        return pageList;
    }
}
