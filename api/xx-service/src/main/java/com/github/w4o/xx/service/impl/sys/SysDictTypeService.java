package com.github.w4o.xx.service.impl.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysDictTypeEntity;
import com.github.w4o.xx.service.dto.sys.dict.DictTypeDTO;
import com.github.w4o.xx.service.mapper.SysDictTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典类型服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictTypeService extends BaseServiceImpl<SysDictTypeMapper, SysDictTypeEntity> implements BaseService<SysDictTypeEntity> {

    private final SysDictTypeMapper sysDictTypeMapper;

    /**
     * 获取分页列表
     *
     * @param page   分页参数
     * @param entity 查询参数
     * @return 分页列表
     */
    public Page<DictTypeDTO> getPageList(Page<SysDictTypeEntity> page, SysDictTypeEntity entity) {
        Page<DictTypeDTO> pageList = sysDictTypeMapper.findPage(page, entity);
        handlePageRecord(pageList);
        return pageList;
    }
}
