package com.github.w4o.xx.manage.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import com.github.w4o.xx.manage.param.cms.post.PostPageParam;
import com.github.w4o.xx.manage.param.cms.post.PostParam;
import com.github.w4o.xx.manage.service.CmsPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public void add(PostParam param) {
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
    @Transactional(rollbackFor = Exception.class)
    public void update(long id, PostParam param) {
        CmsPostEntity entity = baseMapper.selectById(id);
        AssertUtils.notNull(ErrorCode.E1204);
        BeanUtils.copyProperties(param, entity);
        baseMapper.updateById(entity);

        // 处理标签
        Set<Long> tagIds = cmsPostTagMapper.findTagIdsByPostId(id);
        // 删除的集合
        List<Long> removedTagIds = CollectionUtil.subtractToList(tagIds, param.getTagIds());
        removedTagIds.forEach(tagId -> cmsPostTagMapper.delete(new LambdaQueryWrapper<CmsPostTagEntity>()
                .eq(CmsPostTagEntity::getPostId, id)
                .eq(CmsPostTagEntity::getTagId, tagId)));

        // 新增的集合
        List<Long> addedTagIds = CollectionUtil.subtractToList(param.getTagIds(), tagIds);
        addedTagIds.forEach(tagId -> {
            CmsPostTagEntity cmsPostTagEntity = new CmsPostTagEntity();
            cmsPostTagEntity.setPostId(id);
            cmsPostTagEntity.setTagId(tagId);
            cmsPostTagMapper.insert(cmsPostTagEntity);
        });

        // 处理分类
        Set<Long> categoryIds = cmsPostCategoryMapper.findCategoryIdsByPostId(id);
        // 删除的集合
        List<Long> removedCategoryIds = CollectionUtil.subtractToList(categoryIds, param.getCategoryIds());
        removedCategoryIds.forEach(categoryId -> cmsPostCategoryMapper.delete(new LambdaQueryWrapper<CmsPostCategoryEntity>()
                .eq(CmsPostCategoryEntity::getPostId, id)
                .eq(CmsPostCategoryEntity::getCategoryId, categoryId)));

        // 新增的集合
        List<Long> addedCategoryIds = CollectionUtil.subtractToList(param.getCategoryIds(), categoryIds);
        addedCategoryIds.forEach(categoryId -> {
            CmsPostCategoryEntity cmsPostCategoryEntity = new CmsPostCategoryEntity();
            cmsPostCategoryEntity.setPostId(id);
            cmsPostCategoryEntity.setCategoryId(categoryId);
            cmsPostCategoryMapper.insert(cmsPostCategoryEntity);
        });
    }

    @Override
    public void delete(long id) {
        baseMapper.deleteById(id);
        cmsPostCategoryMapper.delete(new LambdaQueryWrapper<CmsPostCategoryEntity>().eq(CmsPostCategoryEntity::getPostId, id));
        cmsPostTagMapper.delete(new LambdaQueryWrapper<CmsPostTagEntity>().eq(CmsPostTagEntity::getPostId, id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> detail(Long postId) {
        Map<String, Object> result = baseMapper.findDetailById(postId);
        AssertUtils.notNull(ErrorCode.E1204);

        Set<Long> tagIds = cmsPostTagMapper.findTagIdsByPostId(postId);
        result.put("tagIds", tagIds);

        Set<Long> categoryIds = cmsPostCategoryMapper.findCategoryIdsByPostId(postId);
        result.put("categoryIds", categoryIds);

        return result;
    }

    @Override
    public void updateStatus(Long postId, Integer status) {
        CmsPostEntity entity = baseMapper.selectById(postId);
        AssertUtils.notNull(ErrorCode.E1204);
        entity.setStatus(status);
        baseMapper.updateById(entity);
    }
}
