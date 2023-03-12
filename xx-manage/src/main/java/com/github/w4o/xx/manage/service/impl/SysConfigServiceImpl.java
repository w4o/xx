package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.constant.Constant;
import com.github.w4o.xx.core.entity.SysConfigEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.mapper.SysConfigMapper;
import com.github.w4o.xx.manage.param.sys.config.AddConfigParam;
import com.github.w4o.xx.manage.param.sys.config.ConfigPageParam;
import com.github.w4o.xx.manage.param.sys.config.ModifyConfigParam;
import com.github.w4o.xx.manage.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 系统配置服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysConfigServiceImpl implements SysConfigService {

    private final SysConfigMapper sysConfigMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Page<Map<String, Object>> getPageList(ConfigPageParam param) {
        return sysConfigMapper.selectMapsPage(new Page<>(param.getPageNo(), param.getPageSize()),
                new LambdaQueryWrapper<SysConfigEntity>()
                        .select(SysConfigEntity::getId,
                                SysConfigEntity::getConfigKey,
                                SysConfigEntity::getConfigValue,
                                SysConfigEntity::getLastUpdateTime)
                        .orderByAsc(SysConfigEntity::getConfigKey));
    }

    @Override
    public void add(AddConfigParam param) {
        // 检查key是否存在
        if (sysConfigMapper.selectCount(new LambdaQueryWrapper<SysConfigEntity>()
                .eq(SysConfigEntity::getConfigKey, param.getConfigKey())) > 0) {
            throw new CustomException(ErrorCode.E1007);
        }
        SysConfigEntity entity = new SysConfigEntity();
        BeanUtils.copyProperties(param, entity);
        sysConfigMapper.insert(entity);

        // 清除缓存
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(Constant.REDIS_CACHE_PREFIX + "*")));
    }

    @Override
    public void update(long id, ModifyConfigParam param) {
        SysConfigEntity entity = sysConfigMapper.selectById(id);
        AssertUtils.notNull(entity);
        BeanUtils.copyProperties(param, entity);
        sysConfigMapper.updateById(entity);

        // 清除缓存
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(Constant.REDIS_CACHE_PREFIX + "*")));
    }

    @Override
    public void delete(long id) {
        sysConfigMapper.deleteById(id);
    }
}
