package com.github.w4o.xx.core.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.w4o.xx.core.base.dto.BaseDataDTO;

import java.util.List;

/**
 * @author Frank
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 处理分页数据
     *
     * @param page 分页数据
     */
    void handlePageRecord(Page<? extends BaseDataDTO> page);

    /**
     * 处理列表数据
     *
     * @param list 列表数据
     */
    void handleListData(List<? extends BaseDataDTO> list);

    /**
     * 处理单条数据
     *
     * @param data 单条数据
     */
    void handleOneData(BaseDataDTO data);
}
