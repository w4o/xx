package com.github.w4o.xx.service.impl.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysConfigEntity;
import com.github.w4o.xx.service.dto.sys.config.SysConfigDTO;
import com.github.w4o.xx.service.mapper.SysConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统配置服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysConfigService extends BaseServiceImpl<SysConfigMapper, SysConfigEntity> implements BaseService<SysConfigEntity> {

    /**
     * 获取分页列表
     *
     * @param page   分页参数
     * @param entity 查询条件
     * @return 分页列表
     */
    public Page<SysConfigDTO> getPageList(Page<SysConfigEntity> page, SysConfigEntity entity) {
        Page<SysConfigDTO> pageList = baseMapper.findPage(page, entity);
        handlePageRecord(pageList);
        return pageList;
    }
}
