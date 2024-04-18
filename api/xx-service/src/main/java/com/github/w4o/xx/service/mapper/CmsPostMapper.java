package com.github.w4o.xx.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.CmsPostEntity;
import com.github.w4o.xx.service.dto.cms.post.PostDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 文章表 Mapper 接口
 *
 * @author Frank
 */
public interface CmsPostMapper extends BaseMapper<CmsPostEntity> {

    /**
     * 分页查询
     *
     * @param page      分页参数
     * @param condition 查询参数
     * @return 分页数据
     */
    Page<PostDTO> findPage(Page<?> page, @Param("condition") Map<String, Object> condition);

    /**
     * 获取文章详细信息
     *
     * @param postId 文章ID
     * @return 文章详细信息
     */
    PostDTO findDetailById(Long postId);

}
