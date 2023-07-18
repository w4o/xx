package com.github.w4o.xx.manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.entity.CmsPostEntity;
import com.github.w4o.xx.manage.dto.cms.post.PostDTO;
import com.github.w4o.xx.manage.param.cms.post.AddPostParam;
import com.github.w4o.xx.manage.param.cms.post.ModifyPostParam;
import com.github.w4o.xx.manage.param.cms.post.PostPageParam;

import java.util.Map;

/**
 * 文章服务接口
 *
 * @author Frank
 */
public interface CmsPostService extends BaseService<CmsPostEntity> {
    /**
     * 获取分页列表
     *
     * @param param 请求参数
     * @return 分页列表
     */
    Page<PostDTO> getPageList(PostPageParam param);

    /**
     * 添加文章
     *
     * @param param 请求参数
     */
    void add(AddPostParam param);

    /**
     * 修改文章
     *
     * @param id    文章id
     * @param param 请求参数
     */
    void update(long id, ModifyPostParam param);

    /**
     * 删除文章
     *
     * @param id 文章id
     */
    void delete(long id);

    /**
     * 文章详情
     *
     * @param postId 文章id
     * @return 文章详情
     */
    Map<String, Object> detail(Long postId);

    /**
     * 更新文章状态
     *
     * @param postId 文章id
     * @param status 文章状态
     */
    void updateStatus(Long postId, Integer status);

}
