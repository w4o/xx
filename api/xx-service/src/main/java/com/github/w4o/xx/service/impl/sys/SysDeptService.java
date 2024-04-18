package com.github.w4o.xx.service.impl.sys;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysDeptEntity;
import com.github.w4o.xx.core.entity.SysUserEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.service.mapper.SysDeptMapper;
import com.github.w4o.xx.service.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门服务实现类
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysDeptService extends BaseServiceImpl<SysDeptMapper, SysDeptEntity> implements BaseService<SysDeptEntity> {

    private final SysUserMapper sysUserMapper;

    /**
     * 添加部门
     *
     * @param entity 部门实体
     */
    public void add(SysDeptEntity entity) {
        boolean exists = baseMapper.exists(new LambdaQueryWrapper<SysDeptEntity>()
                .eq(SysDeptEntity::getDeptName, entity.getDeptName())
                .eq(SysDeptEntity::getParentId, entity.getParentId()));
        if (exists) {
            throw new CustomException(ErrorCode.E1015);
        }
        baseMapper.insert(entity);
    }

    /**
     * 删除部门
     *
     * @param id 部门id
     */
    public void delete(Long id) {
        // 判断是否存在下级部门
        Long deptCount = baseMapper.selectCount(new LambdaQueryWrapper<SysDeptEntity>().eq(SysDeptEntity::getParentId, id));
        if (deptCount.compareTo(0L) > 0) {
            throw new CustomException(ErrorCode.E1004);
        }
        // 判断部门下是否存在用户
        Long userCount = sysUserMapper.selectCount(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getDeptId, id));
        if (userCount.compareTo(0L) > 0) {
            throw new CustomException(ErrorCode.E1005);
        }
        baseMapper.deleteById(id);
    }

    /**
     * 修改部门信息
     *
     * @param entity 部门实体
     */
    public void update(SysDeptEntity entity) {
        SysDeptEntity queryEntity = baseMapper.selectById(entity.getId());
        AssertUtils.notNull(queryEntity);
        boolean exists = baseMapper.exists(new LambdaQueryWrapper<SysDeptEntity>()
                .eq(SysDeptEntity::getDeptName, entity.getDeptName())
                .eq(SysDeptEntity::getParentId, queryEntity.getParentId())
                .ne(SysDeptEntity::getId, entity.getId()));
        if (exists) {
            throw new CustomException(ErrorCode.E1015);
        }
        BeanUtils.copyProperties(entity, queryEntity);
        baseMapper.updateById(queryEntity);
    }

    /**
     * 查询部门树
     *
     * @return 部门树
     */
    public List<Tree<Long>> findTree() {
        List<SysDeptEntity> deptList = baseMapper.selectList(new QueryWrapper<>());
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("sort");
        treeNodeConfig.setDeep(3);
        return TreeUtil.build(deptList, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getSort().toString());
            tree.setName(treeNode.getDeptName());
            tree.put("enabled", treeNode.getEnabled());
            tree.put("description", treeNode.getDescription());
            tree.put("createTime", treeNode.getCreateTime());
            tree.put("deptName", treeNode.getDeptName());
        });
    }
}
