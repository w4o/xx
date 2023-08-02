package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.CmsPostTagEntity;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * 文章标签关系表 Mapper 接口
 *
 * @author Frank
 */
public interface CmsPostTagMapper extends BaseMapper<CmsPostTagEntity> {
    /**
     * 根据文章ID获取所有标签Id集合
     *
     * @param postId 文章ID
     * @return 文章所有标签Id集合
     */
    @Select("select tag_id from cms_post_tag where post_id = #{postId} and deleted = 0")
    Set<Long> findTagIdsByPostId(Long postId);
}
