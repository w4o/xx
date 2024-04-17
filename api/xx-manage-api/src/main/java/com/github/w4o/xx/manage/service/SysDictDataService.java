package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.SysDictDataEntity;
import com.github.w4o.xx.manage.dto.sys.dict.DictDataDTO;
import com.github.w4o.xx.manage.param.sys.dict.AddDictDataParam;
import com.github.w4o.xx.manage.param.sys.dict.DictDataPageParam;
import com.github.w4o.xx.manage.param.sys.dict.ModifyDictDataParam;

/**
 * 系统字典服务接口
 *
 * @author Frank
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    /**
     * 添加字典
     *
     * @param param 请求参数
     */
    void add(AddDictDataParam param);

    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<DictDataDTO> getPageList(DictDataPageParam param);

    /**
     * 修改字典信息
     *
     * @param id    字典id
     * @param param 请求参数
     */
    void update(Long id, ModifyDictDataParam param);

    /**
     * 删除字典
     *
     * @param id 字典id
     */
    void delete(Long id);

}
