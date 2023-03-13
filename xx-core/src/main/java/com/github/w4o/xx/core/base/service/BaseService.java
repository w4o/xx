package com.github.w4o.xx.core.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.w4o.xx.core.base.dto.BasePageDataDTO;

/**
 * @author Frank
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 处理分页数据
     *
     * @param page 分页数据
     */
    void handlePageRecord(Page<? extends BasePageDataDTO> page);
}
