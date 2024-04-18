package com.github.w4o.xx.service.impl.sys;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysMediaCategoryEntity;
import com.github.w4o.xx.service.mapper.SysMediaCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体分类服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMediaCategoryService extends BaseServiceImpl<SysMediaCategoryMapper, SysMediaCategoryEntity> implements BaseService<SysMediaCategoryEntity> {

    /**
     * 获取媒体分类树
     *
     * @return 媒体分类树
     */
    public List<Tree<Long>> getTree() {
        List<SysMediaCategoryEntity> list = baseMapper.selectList(new LambdaQueryWrapper<>());
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("name");
        return TreeUtil.build(list, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getId().toString());
            tree.setName(treeNode.getName());
        });
    }
}
