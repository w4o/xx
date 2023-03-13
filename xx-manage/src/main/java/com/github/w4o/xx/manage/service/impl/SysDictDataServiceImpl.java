package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.constant.Constant;
import com.github.w4o.xx.core.entity.SysDictDataEntity;
import com.github.w4o.xx.core.entity.SysDictTypeEntity;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.dto.sys.dict.DictDataPageDTO;
import com.github.w4o.xx.manage.mapper.SysDictDataMapper;
import com.github.w4o.xx.manage.mapper.SysDictTypeMapper;
import com.github.w4o.xx.manage.param.sys.dict.AddDictDataParam;
import com.github.w4o.xx.manage.param.sys.dict.DictDataPageParam;
import com.github.w4o.xx.manage.param.sys.dict.ModifyDictDataParam;
import com.github.w4o.xx.manage.service.SysDictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 字典数据服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictDataMapper, SysDictDataEntity> implements SysDictDataService {

    private final SysDictDataMapper sysDictDataMapper;
    private final SysDictTypeMapper sysDictTypeMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void add(AddDictDataParam param) {
        // 获取字典类型数据
        SysDictTypeEntity querySysDictType = sysDictTypeMapper.selectById(param.getDictTypeId());
        AssertUtils.notNull(querySysDictType);

        SysDictDataEntity sysDictEntity = new SysDictDataEntity();
        BeanUtils.copyProperties(param, sysDictEntity);
        sysDictEntity.setLabel(querySysDictType.getLabel());
        sysDictEntity.setSysDictTypeId(param.getDictTypeId());
        sysDictDataMapper.insert(sysDictEntity);

        // 清除缓存
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(Constant.REDIS_CACHE_PREFIX + "*")));
    }

    @Override
    public Page<DictDataPageDTO> getPageList(DictDataPageParam param) {
        Page<DictDataPageDTO> page = sysDictDataMapper.findPage(new Page<>(param.getPageNo(), param.getDictTypeId()), param);
        handlePageRecord(page);
        return page;
    }

    @Override
    public void update(Long id, ModifyDictDataParam param) {
        SysDictDataEntity queryEntity = sysDictDataMapper.selectById(id);
        // 判断数据是否存在
        AssertUtils.notNull(queryEntity);
        BeanUtils.copyProperties(param, queryEntity);
        sysDictDataMapper.updateById(queryEntity);

        // 清除缓存
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(Constant.REDIS_CACHE_PREFIX + "*")));
    }

    @Override
    public void delete(Long id) {
        sysDictDataMapper.deleteById(id);
        // 清除缓存
        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys(Constant.REDIS_CACHE_PREFIX + "*")));
    }

}
