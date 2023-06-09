package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysDictTypeEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.dto.sys.dict.DictTypeDTO;
import com.github.w4o.xx.manage.mapper.SysDictTypeMapper;
import com.github.w4o.xx.manage.param.sys.dict.AddDictTypeParam;
import com.github.w4o.xx.manage.param.sys.dict.DictTypePageParam;
import com.github.w4o.xx.manage.param.sys.dict.ModifyDictTypeParam;
import com.github.w4o.xx.manage.service.SysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典类型服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeMapper, SysDictTypeEntity> implements SysDictTypeService {

    private final SysDictTypeMapper sysDictTypeMapper;

    @Override
    public Page<DictTypeDTO> getPageList(DictTypePageParam param) {
        Page<DictTypeDTO> page = sysDictTypeMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
        handlePageRecord(page);
        return page;
    }

    @Override
    public void add(AddDictTypeParam param) {
        long count = sysDictTypeMapper.selectCount(new LambdaQueryWrapper<SysDictTypeEntity>()
                .eq(SysDictTypeEntity::getLabel, param.getLabel()));
        if (count > 0) {
            throw new CustomException(ErrorCode.E1014);
        }

        SysDictTypeEntity sysDictType = new SysDictTypeEntity();
        BeanUtils.copyProperties(param, sysDictType);
        sysDictTypeMapper.insert(sysDictType);
    }

    @Override
    public void modify(long id, ModifyDictTypeParam param) {
        SysDictTypeEntity querySysDictType = sysDictTypeMapper.selectById(id);
        AssertUtils.notNull(querySysDictType);
        querySysDictType.setName(param.getName());
        sysDictTypeMapper.updateById(querySysDictType);
    }

    @Override
    public void delete(long id) {
        sysDictTypeMapper.deleteById(id);
    }

    /**
     * 得到标签信息
     *
     * @param id 字典id
     * @return 字典实体
     */
    @Override
    public SysDictTypeEntity getInfo(Long id) {
        return sysDictTypeMapper.selectOne(
                new LambdaQueryWrapper<SysDictTypeEntity>()
                        .select(
                                SysDictTypeEntity::getLabel
                        )
                        .eq(
                                SysDictTypeEntity::getId, id
                        )
        );
    }
}
