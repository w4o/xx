package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysDictTypeEntity;
import com.github.w4o.xx.manage.param.sys.dict.AddDictTypeParam;
import com.github.w4o.xx.manage.param.sys.dict.ModifyDictTypeParam;

import java.util.Map;

/**
 * 系统字典服务接口
 *
 * @author Frank
 */
public interface SysDictTypeService {

    /**
     * 添加字典类型
     *
     * @param param 请求参数
     */
    void add(AddDictTypeParam param);

    /**
     * 修改字典类型
     *
     * @param id    字典类型id
     * @param param 请求参数
     */
    void modify(long id, ModifyDictTypeParam param);

    /**
     * 删除字典类型
     *
     * @param id 字典类型id
     */
    void delete(long id);

    /**
     * 获取分页列表
     *
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return 分页列表
     */
    Page<Map<String, Object>> getPageList(long pageNo, long pageSize);


    /**
     * 得到标签信息
     *
     * @param id 字典id
     * @return 字典实体
     */
    SysDictTypeEntity getInfo(Long id);
}
