package com.github.w4o.xx.manage.service.impl;

import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.CmsCategoryEntity;
import com.github.w4o.xx.core.entity.CmsPostCategoryEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.manage.dto.cms.category.CategoryDTO;
import com.github.w4o.xx.manage.mapper.CmsCategoryMapper;
import com.github.w4o.xx.manage.mapper.CmsPostCategoryMapper;
import com.github.w4o.xx.manage.param.cms.category.CategoryPageParam;
import com.github.w4o.xx.manage.param.cms.category.CategoryParam;
import com.github.w4o.xx.manage.service.CmsCategoryService;
import com.github.w4o.xx.manage.vo.cms.category.CategoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章分类服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CmsCategoryServiceImpl extends BaseServiceImpl<CmsCategoryMapper, CmsCategoryEntity> implements CmsCategoryService {

    private final CmsPostCategoryMapper cmsPostCategoryMapper;

    @Override
    public Page<CategoryDTO> getPageList(CategoryPageParam param) {
        Page<CategoryDTO> page = baseMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
        handlePageRecord(page);
        page.getRecords().forEach(dto -> dto.setPostCount(cmsPostCategoryMapper.selectCount(new LambdaQueryWrapper<CmsPostCategoryEntity>()
                .eq(CmsPostCategoryEntity::getCategoryId, dto.getCategoryId()))));
        return page;
    }

    @Override
    public CategoryVO add(CategoryParam param) {
        boolean exists = baseMapper.exists(new LambdaQueryWrapper<CmsCategoryEntity>()
                .eq(CmsCategoryEntity::getName, param.getName()));
        if (exists) {
            throw new CustomException(ErrorCode.E1201);
        }
        CmsCategoryEntity entity = new CmsCategoryEntity();
        BeanUtils.copyProperties(param, entity);
        baseMapper.insert(entity);

        return CategoryVO.builder()
                .categoryId(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .thumbnail(entity.getThumbnail())
                .build();
    }

    @Override
    public void update(long id, CategoryParam param) {

        boolean exists = baseMapper.exists(new LambdaQueryWrapper<CmsCategoryEntity>()
                .eq(CmsCategoryEntity::getName, param.getName())
                .ne(CmsCategoryEntity::getId, id));
        if (exists) {
            throw new CustomException(ErrorCode.E1201);
        }
        CmsCategoryEntity entity = baseMapper.selectById(id);
        BeanUtils.copyProperties(param, entity);
        baseMapper.updateById(entity);
    }

    @Override
    public void delete(long id) {
        boolean exists = cmsPostCategoryMapper.exists(new LambdaQueryWrapper<CmsPostCategoryEntity>()
                .eq(CmsPostCategoryEntity::getCategoryId, id));
        if (exists) {
            throw new CustomException(ErrorCode.E1203);
        }
        baseMapper.deleteById(id);
    }

    @Override
    public List<?> getTree() {
        List<CmsCategoryEntity> categoryList = baseMapper.selectList(new LambdaQueryWrapper<>());

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();

        return TreeUtil.build(categoryList, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getId().toString());
            tree.setName(treeNode.getName());
        });
    }
}
