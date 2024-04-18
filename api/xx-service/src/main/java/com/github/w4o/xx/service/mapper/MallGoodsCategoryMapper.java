package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.MallGoodsCategoryEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author Frank
 */
public interface MallGoodsCategoryMapper extends BaseMapper<MallGoodsCategoryEntity> {

    /**
     * 根据分类名称和父级id查询是否存在
     *
     * @param categoryName 分类名称
     * @param parentId     父级id
     * @return 是否存在
     */
    boolean existsByCategoryNameAndParentId(@Param("categoryName") String categoryName, @Param("parentId") Long parentId, @Param("id") Long id);
}
