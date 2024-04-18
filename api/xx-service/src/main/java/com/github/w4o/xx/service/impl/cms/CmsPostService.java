package com.github.w4o.xx.service.impl.cms;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.CmsPostCategoryEntity;
import com.github.w4o.xx.core.entity.CmsPostEntity;
import com.github.w4o.xx.core.entity.CmsPostTagEntity;
import com.github.w4o.xx.core.entity.CmsTagEntity;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.service.dto.cms.post.PostDTO;
import com.github.w4o.xx.service.mapper.CmsPostCategoryMapper;
import com.github.w4o.xx.service.mapper.CmsPostMapper;
import com.github.w4o.xx.service.mapper.CmsPostTagMapper;
import com.github.w4o.xx.service.mapper.CmsTagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.github.w4o.xx.core.entity.CmsPostEntity.STATUS_MAP;

/**
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CmsPostService extends BaseServiceImpl<CmsPostMapper, CmsPostEntity> implements BaseService<CmsPostEntity> {

    private final CmsPostTagMapper cmsPostTagMapper;
    private final CmsPostCategoryMapper cmsPostCategoryMapper;
    private final CmsTagMapper cmsTagMapper;

    /**
     * 获取分页列表
     *
     * @param page      分页参数
     * @param condition 请求参数
     * @return 分页列表
     */
    public Page<PostDTO> getPageList(Page<?> page, Map<String, Object> condition) {
        Page<PostDTO> pageList = baseMapper.findPage(page, condition);
        handlePageRecord(pageList);
        pageList.getRecords().forEach(postDTO -> postDTO.setStatusValue(STATUS_MAP.get(postDTO.getStatus())));
        return pageList;
    }

    /**
     * 添加文章
     *
     * @param entity      文章实体
     * @param tags        标签
     * @param categoryIds 分类
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(CmsPostEntity entity, Set<String> tags, Set<Long> categoryIds) {

        baseMapper.insert(entity);

        Long postId = entity.getId();
        // 保存标签
        if (tags != null) {
            tags.forEach(tag -> {
                CmsTagEntity tagEntity = cmsTagMapper.selectOne(new LambdaQueryWrapper<CmsTagEntity>()
                        .eq(CmsTagEntity::getName, tag));

                if (tagEntity == null) {
                    tagEntity = new CmsTagEntity();
                    tagEntity.setName(tag);
                    cmsTagMapper.insert(tagEntity);
                }

                CmsPostTagEntity cmsPostTagEntity = new CmsPostTagEntity();
                cmsPostTagEntity.setPostId(postId);
                cmsPostTagEntity.setTagId(tagEntity.getId());
                cmsPostTagMapper.insert(cmsPostTagEntity);
            });
        }

        // 保存分类
        if (categoryIds != null) {
            categoryIds.forEach(categoryId -> {
                CmsPostCategoryEntity cmsPostCategoryEntity = new CmsPostCategoryEntity();
                cmsPostCategoryEntity.setPostId(postId);
                cmsPostCategoryEntity.setCategoryId(categoryId);
                cmsPostCategoryMapper.insert(cmsPostCategoryEntity);
            });
        }
    }

    /**
     * 修改文章
     *
     * @param entity      文章实体
     * @param tags        标签
     * @param categoryIds 分类
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(CmsPostEntity entity, Set<String> tags, Set<Long> categoryIds) {

        Long id = entity.getId();

        baseMapper.updateById(entity);

        // 处理标签
        Set<Long> postTagIds = cmsPostTagMapper.findTagIdsByPostId(id);

        Set<Long> paramTagIds = new HashSet<>();
        tags.forEach(tag -> {
            CmsTagEntity tagEntity = cmsTagMapper.selectOne(new LambdaQueryWrapper<CmsTagEntity>()
                    .eq(CmsTagEntity::getName, tag));

            if (tagEntity == null) {
                tagEntity = new CmsTagEntity();
                tagEntity.setName(tag);
                cmsTagMapper.insert(tagEntity);
            }
            paramTagIds.add(tagEntity.getId());
        });

        // 删除的集合
        List<Long> removedTagIds = CollUtil.subtractToList(postTagIds, paramTagIds);
        removedTagIds.forEach(tagId -> cmsPostTagMapper.delete(new LambdaQueryWrapper<CmsPostTagEntity>()
                .eq(CmsPostTagEntity::getPostId, id)
                .eq(CmsPostTagEntity::getTagId, tagId)));

        // 新增的集合
        List<Long> addedTagIds = CollUtil.subtractToList(paramTagIds, postTagIds);
        addedTagIds.forEach(tagId -> {
            CmsPostTagEntity cmsPostTagEntity = new CmsPostTagEntity();
            cmsPostTagEntity.setPostId(id);
            cmsPostTagEntity.setTagId(tagId);
            cmsPostTagMapper.insert(cmsPostTagEntity);
        });

        // 处理分类
        Set<Long> postCategoryIds = cmsPostCategoryMapper.findCategoryIdsByPostId(id);
        // 删除的集合
        List<Long> removedCategoryIds = CollUtil.subtractToList(postCategoryIds, categoryIds);
        removedCategoryIds.forEach(categoryId -> cmsPostCategoryMapper.delete(new LambdaQueryWrapper<CmsPostCategoryEntity>()
                .eq(CmsPostCategoryEntity::getPostId, id)
                .eq(CmsPostCategoryEntity::getCategoryId, categoryId)));

        // 新增的集合
        List<Long> addedCategoryIds = CollUtil.subtractToList(categoryIds, postCategoryIds);
        addedCategoryIds.forEach(categoryId -> {
            CmsPostCategoryEntity cmsPostCategoryEntity = new CmsPostCategoryEntity();
            cmsPostCategoryEntity.setPostId(id);
            cmsPostCategoryEntity.setCategoryId(categoryId);
            cmsPostCategoryMapper.insert(cmsPostCategoryEntity);
        });
    }

    /**
     * 删除文章
     *
     * @param id 文章id
     */
    public void delete(long id) {
        baseMapper.deleteById(id);
        cmsPostCategoryMapper.delete(new LambdaQueryWrapper<CmsPostCategoryEntity>().eq(CmsPostCategoryEntity::getPostId, id));
        cmsPostTagMapper.delete(new LambdaQueryWrapper<CmsPostTagEntity>().eq(CmsPostTagEntity::getPostId, id));
    }

    /**
     * 文章详情
     *
     * @param postId 文章id
     * @return 文章详情
     */
    @Transactional(rollbackFor = Exception.class)
    public PostDTO detail(Long postId) {
        PostDTO result = baseMapper.findDetailById(postId);
        AssertUtils.notNull(ErrorCode.E1204);
        result.setStatusValue(STATUS_MAP.get(result.getStatus()));
        return result;
    }

}
