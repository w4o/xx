package com.github.w4o.xx.core.manage;

import com.github.w4o.xx.core.entity.SysDictDataEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统字典组件接口
 *
 * @author Frank
 */
public interface SysDictManage {

    /**
     * 根据字典标签获取字典数据
     *
     * @param label 标签
     * @return 字典数据
     */
    List<Map<String, Object>> getDictMapByLabel(String label);

    /**
     * 根据id获取 字典对象
     *
     * @param id 主键id
     * @return 字典对象
     */
    SysDictDataEntity getDictById(Long id);

    /**
     * 根据id获取字典值
     *
     * @param id 字典id
     * @return 值
     */
    String getDictValueById(Long id);
}
