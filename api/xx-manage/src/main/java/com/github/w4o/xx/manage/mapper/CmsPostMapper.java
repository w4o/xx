package com.github.w4o.xx.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.entity.CmsPostEntity;
import com.github.w4o.xx.manage.dto.cms.post.PostDTO;
import com.github.w4o.xx.manage.param.cms.post.PostPageParam;

/**
 * 文章表表 Mapper 接口
 *
 * @author Frank
 */
public interface CmsPostMapper extends BaseMapper<CmsPostEntity> {
    /**
     * 分页查询
     *
     * @param page  分页参数
     * @param param 查询参数
     * @return 分页数据
     */
    Page<PostDTO> findPage(Page<?> page, PostPageParam param);
}
