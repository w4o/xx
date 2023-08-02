package com.github.w4o.xx.manage.service.impl;

import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysMediaCategoryEntity;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.mapper.SysMediaCategoryMapper;
import com.github.w4o.xx.manage.param.sys.media.category.MediaCategoryParam;
import com.github.w4o.xx.manage.service.SysMediaCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
public class SysMediaCategoryServiceImpl extends BaseServiceImpl<SysMediaCategoryMapper, SysMediaCategoryEntity> implements SysMediaCategoryService {

    @Override
    public void add(MediaCategoryParam param) {
        boolean exists = baseMapper.exists(new LambdaQueryWrapper<SysMediaCategoryEntity>()
                .eq(SysMediaCategoryEntity::getName, param.getName())
                .eq(SysMediaCategoryEntity::getParentId, param.getParentId()));
        AssertUtils.isFalse(exists, ErrorCode.E1016);
        SysMediaCategoryEntity entity = new SysMediaCategoryEntity();
        BeanUtils.copyProperties(param, entity);
        baseMapper.insert(entity);
    }

    @Override
    public void update(long id, MediaCategoryParam param) {
        boolean exists = baseMapper.exists(new LambdaQueryWrapper<SysMediaCategoryEntity>()
                .eq(SysMediaCategoryEntity::getParentId, param.getParentId())
                .eq(SysMediaCategoryEntity::getName, param.getName())
                .ne(SysMediaCategoryEntity::getId, id));
        AssertUtils.isFalse(exists, ErrorCode.E1016);
        SysMediaCategoryEntity entity = baseMapper.selectById(id);
        AssertUtils.notNull(entity, ErrorCode.E1001);
        BeanUtils.copyProperties(param, entity);
        baseMapper.updateById(entity);
    }

    @Override
    public void delete(long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public List<?> getTree() {
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
