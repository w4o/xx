package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.CmsPostCategoryEntity;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * 文章分类关系表 Mapper 接口
 *
 * @author Frank
 */
public interface CmsPostCategoryMapper extends BaseMapper<CmsPostCategoryEntity> {
    /**
     * 根据文章ID获取所有分类Id集合
     *
     * @param postId 文章ID
     * @return 文章所有分类Id集合
     */
    @Select("select category_id from cms_post_category where post_id = #{postId} and deleted = 0")
    Set<Long> findCategoryIdsByPostId(Long postId);
}
