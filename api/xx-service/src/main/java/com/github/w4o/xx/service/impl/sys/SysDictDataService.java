package com.github.w4o.xx.service.impl.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysDictDataEntity;
import com.github.w4o.xx.service.dto.sys.dict.DictDataDTO;
import com.github.w4o.xx.service.mapper.SysDictDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 字典数据服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDictDataService extends BaseServiceImpl<SysDictDataMapper, SysDictDataEntity> implements BaseService<SysDictDataEntity> {

    private final SysDictDataMapper sysDictDataMapper;

    /**
     * 获取分页列表
     *
     * @param page   分页参数
     * @param entity 查询参数
     * @return 分页列表
     */
    public Page<DictDataDTO> getPageList(Page<SysDictDataEntity> page, SysDictDataEntity entity) {
        Page<DictDataDTO> pageList = sysDictDataMapper.findPage(page, entity);
        handlePageRecord(pageList);
        return pageList;
    }

}
