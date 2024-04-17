package com.github.w4o.xx.applet.service;

import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.CmsPostEntity;

import java.util.Map;

/**
 * @author Frank
 */
public interface CmsPostService extends BaseService<CmsPostEntity> {

    /**
     * 文章详情
     *
     * @param postId 文章ID
     * @return 文章详情
     */
    Map<String, Object> detail(Long postId);
}
