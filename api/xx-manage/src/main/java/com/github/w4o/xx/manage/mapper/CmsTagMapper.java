package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.CmsTagEntity;
import com.github.w4o.xx.manage.dto.cms.tag.TagDTO;
import com.github.w4o.xx.manage.param.cms.tag.TagPageParam;

/**
 * 文章标签表 Mapper 接口
 *
 * @author Frank
 */
public interface CmsTagMapper extends BaseMapper<CmsTagEntity> {
    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    Page<TagDTO> findPage(Page<?> page, TagPageParam param);
}
