package com.github.w4o.xx.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.w4o.xx.core.entity.CmsPostEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author Frank
 */
public interface CmsPostMapper extends BaseMapper<CmsPostEntity> {
    /**
     * 文章详情
     *
     * @param postId 文章ID
     * @return 文章详情
     */
    @Select("select id, title, summary, content, thumbnail from cms_post where deleted = 0 and status = 2 and id = #{postId}")
    Map<String, Object> getDetailById(@Param("postId") long postId);
}
