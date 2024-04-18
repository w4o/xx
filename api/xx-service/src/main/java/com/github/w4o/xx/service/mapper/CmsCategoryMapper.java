package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.CmsCategoryEntity;
import com.github.w4o.xx.service.dto.cms.category.CategoryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 文章分类表 Mapper 接口
 *
 * @author Frank
 */
public interface CmsCategoryMapper extends BaseMapper<CmsCategoryEntity> {
    /**
     * 分页查询
     *
     * @param page      分页参数
     * @param condition 查询参数
     * @return 分页数据
     */
    Page<CategoryDTO> findPage(Page<?> page, @Param("condition") Map<String, Object> condition);

}
