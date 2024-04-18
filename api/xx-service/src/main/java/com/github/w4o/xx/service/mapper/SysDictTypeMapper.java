package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.SysDictTypeEntity;
import com.github.w4o.xx.service.dto.sys.dict.DictTypeDTO;
import org.apache.ibatis.annotations.Param;

/**
 * 系统字典类型表 Mapper 接口
 *
 * @author Frank
 */
public interface SysDictTypeMapper extends BaseMapper<SysDictTypeEntity> {

    /**
     * 分页查询
     *
     * @param page   分页参数
     * @param entity 查询参数
     * @return 分页数据
     */
    Page<DictTypeDTO> findPage(Page<?> page, @Param("condition") SysDictTypeEntity entity);
}
