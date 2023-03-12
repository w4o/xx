package com.github.w4o.xx.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.SysDictDataEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 系统字典表 Mapper 共通接口
 *
 * @author Frank
 */
public interface BaseSysDictDataMapper extends BaseMapper<SysDictDataEntity> {

    /**
     * 根据标签获取字典数据集合
     *
     * @param label 标签
     * @return 字典数据集合
     */
    @Select("select id, value from sys_dict_data where label = #{label} order by sort")
    List<Map<String, Object>> getByLabel(@Param("label") String label);

    /**
     * 根据id查值
     *
     * @param id 主键id
     * @return 值
     */
    @Select("select value from sys_dict_data where id = #{id}")
    String getValueById(@Param("id") long id);
}
