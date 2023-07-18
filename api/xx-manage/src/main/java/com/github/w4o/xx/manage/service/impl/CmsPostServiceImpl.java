package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.CmsPostCategoryEntity;
import com.github.w4o.xx.core.entity.CmsPostEntity;
import com.github.w4o.xx.core.entity.CmsPostTagEntity;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.dto.cms.post.PostDTO;
import com.github.w4o.xx.manage.mapper.CmsPostCategoryMapper;
import com.github.w4o.xx.manage.mapper.CmsPostMapper;
import com.github.w4o.xx.manage.mapper.CmsPostTagMapper;
import com.github.w4o.xx.manage.param.cms.post.AddPostParam;
import com.github.w4o.xx.manage.param.cms.post.ModifyPostParam;
import com.github.w4o.xx.manage.param.cms.post.PostPageParam;
import com.github.w4o.xx.manage.service.CmsPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.github.w4o.xx.core.entity.CmsPostEntity.STATUS_MAP;

/**
 * 文章标签服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CmsPostServiceImpl extends BaseServiceImpl<CmsPostMapper, CmsPostEntity> implements CmsPostService {

    private final CmsPostTagMapper cmsPostTagMapper;
    private final CmsPostCategoryMapper cmsPostCategoryMapper;

    @Override
    public Page<PostDTO> getPageList(PostPageParam param) {
        Page<PostDTO> page = baseMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
        handlePageRecord(page);
        page.getRecords().forEach(postDTO -> postDTO.setStatusValue(STATUS_MAP.get(postDTO.getStatus())));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddPostParam param) {
        CmsPostEntity entity = new CmsPostEntity();
        BeanUtils.copyProperties(param, entity);
        baseMapper.insert(entity);

        Long postId = entity.getId();
        // 保存标签
        if (param.getTagIds() != null) {
            param.getTagIds().forEach(tagId -> {
                CmsPostTagEntity cmsPostTagEntity = new CmsPostTagEntity();
                cmsPostTagEntity.setPostId(postId);
                cmsPostTagEntity.setTagId(tagId);
                cmsPostTagMapper.insert(cmsPostTagEntity);
            });
        }
        // 保存分类
        if (param.getCategoryIds() != null) {
            param.getCategoryIds().forEach(categoryId -> {
                CmsPostCategoryEntity cmsPostCategoryEntity = new CmsPostCategoryEntity();
                cmsPostCategoryEntity.setPostId(postId);
                cmsPostCategoryEntity.setCategoryId(categoryId);
                cmsPostCategoryMapper.insert(cmsPostCategoryEntity);
            });
        }
    }

    @Override
    public void update(long id, ModifyPostParam param) {

    }

    @Override
    public void delete(long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public Map<String, Object> detail(Long postId) {
        return null;
    }

    @Override
    public void updateStatus(Long postId, Integer status) {
        CmsPostEntity entity = baseMapper.selectById(postId);
        AssertUtils.notNull(ErrorCode.E1204);
        entity.setStatus(status);
        baseMapper.updateById(entity);
    }
}
