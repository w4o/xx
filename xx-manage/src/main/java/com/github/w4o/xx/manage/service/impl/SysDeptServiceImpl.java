package com.github.w4o.xx.manage.service.impl;

import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysDeptEntity;
import com.github.w4o.xx.core.entity.SysUserEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.manage.mapper.SysDeptMapper;
import com.github.w4o.xx.manage.mapper.SysUserMapper;
import com.github.w4o.xx.manage.param.sys.dept.AddDeptParam;
import com.github.w4o.xx.manage.param.sys.dept.ModifyDeptParam;
import com.github.w4o.xx.manage.service.SysDeptService;
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
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptMapper, SysDeptEntity> implements SysDeptService {

    private final SysDeptMapper sysDeptMapper;
    private final SysUserMapper sysUserMapper;

    @Override

    public void add(AddDeptParam param) {
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        BeanUtils.copyProperties(param, sysDeptEntity);
        sysDeptMapper.insert(sysDeptEntity);
    }

    @Override
    public void delete(Long id) {
        // 判断是否存在下级部门
        Long deptCount = sysDeptMapper.selectCount(new LambdaQueryWrapper<SysDeptEntity>().eq(SysDeptEntity::getParentId, id));
        if (deptCount.compareTo(0L) > 0) {
            throw new CustomException(ErrorCode.E1004);
        }
        // 判断部门下是否存在用户
        Long userCount = sysUserMapper.selectCount(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getDeptId, id));
        if (userCount.compareTo(0L) > 0) {
            throw new CustomException(ErrorCode.E1005);
        }
        sysDeptMapper.deleteById(id);
    }

    @Override
    public void update(Long id, ModifyDeptParam param) {
        SysDeptEntity queryEntity = sysDeptMapper.selectById(id);
        AssertUtils.notNull(queryEntity);
        queryEntity.setDeptName(param.getDeptName());
        queryEntity.setSort(param.getSort());
        sysDeptMapper.updateById(queryEntity);
    }

    @Override
    public List<?> findTree() {
        List<SysDeptEntity> deptList = sysDeptMapper.selectList(new QueryWrapper<>());
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("sort");
        treeNodeConfig.setDeep(3);
        return TreeUtil.build(deptList, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getSort().toString());
            tree.setName(treeNode.getDeptName());
        });
    }
}
