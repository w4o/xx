package com.github.w4o.xx.service.impl.cms;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.CmsCategoryEntity;
import com.github.w4o.xx.core.entity.CmsPostCategoryEntity;
import com.github.w4o.xx.service.dto.cms.category.CategoryDTO;
import com.github.w4o.xx.service.mapper.CmsCategoryMapper;
import com.github.w4o.xx.service.mapper.CmsPostCategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文章分类服务实现类
 *
 * @author Frank
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CmsCategoryService extends BaseServiceImpl<CmsCategoryMapper, CmsCategoryEntity> implements BaseService<CmsCategoryEntity> {

    private final CmsPostCategoryMapper cmsPostCategoryMapper;

    /**
     * 获取分页列表
     *
     * @param page      分页参数
     * @param condition 查询条件
     * @return 分页列表
     */
    public Page<CategoryDTO> getPageList(Page<?> page, Map<String, Object> condition) {
        Page<CategoryDTO> pageList = baseMapper.findPage(page, condition);
        handlePageRecord(pageList);
        pageList.getRecords().forEach(dto -> dto.setPostCount(cmsPostCategoryMapper.selectCount(new LambdaQueryWrapper<CmsPostCategoryEntity>()
                .eq(CmsPostCategoryEntity::getCategoryId, dto.getCategoryId()))));
        return pageList;
    }

    public List<Tree<Long>> getTree() {
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
