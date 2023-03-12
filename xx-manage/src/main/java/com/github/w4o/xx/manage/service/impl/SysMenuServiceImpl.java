package com.github.w4o.xx.manage.service.impl;

import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.entity.SysMenuEntity;
import com.github.w4o.xx.core.entity.SysRoleMenuEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.manage.common.util.LoginUtils;
import com.github.w4o.xx.manage.mapper.SysMenuMapper;
import com.github.w4o.xx.manage.mapper.SysRoleMenuMapper;
import com.github.w4o.xx.manage.param.sys.menu.MenuParam;
import com.github.w4o.xx.manage.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 菜单服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuServiceImpl implements SysMenuService {

    /**
     * 管理员id
     */
    public static final Long SYS_ROLE_ID = 1L;
    private final SysMenuMapper sysMenuMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MenuParam param) {
        // 检查名称是否重复
        long count = sysMenuMapper.selectCount(new LambdaQueryWrapper<SysMenuEntity>()
                .eq(SysMenuEntity::getName, param.getName()));
        if (count > 0) {
            throw new CustomException(ErrorCode.E1007);
        }
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        BeanUtils.copyProperties(param, sysMenuEntity);
        sysMenuMapper.insert(sysMenuEntity);
        SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
        sysRoleMenuEntity.setSysRoleId(SYS_ROLE_ID);
        sysRoleMenuEntity.setSysMenuId(sysMenuEntity.getId());
        sysRoleMenuMapper.insert(sysRoleMenuEntity);
    }

    @Override
    public void update(long id, MenuParam param) {
        SysMenuEntity queryEntity = sysMenuMapper.selectById(id);
        // 判断数据是否存在
        if (queryEntity == null) {
            throw new CustomException(ErrorCode.E1001);
        }
        // 检查名称是否重复
        long count = sysMenuMapper.selectCount(new LambdaQueryWrapper<SysMenuEntity>()
                .eq(SysMenuEntity::getName, param.getName())
                .ne(SysMenuEntity::getId, id));
        if (count > 0) {
            throw new CustomException(ErrorCode.E1007);
        }
        BeanUtils.copyProperties(param, queryEntity);
        sysMenuMapper.updateById(queryEntity);
    }

    @Override
    public void delete(long id) {
        Long menuCount = sysMenuMapper.selectCount(new LambdaQueryWrapper<SysMenuEntity>().eq(SysMenuEntity::getParentId, id));
        if (menuCount.compareTo(0L) > 0) {
            throw new CustomException(ErrorCode.E1010);
        }
        sysMenuMapper.deleteById(id);
        // 同时删除菜单对应的权限
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenuEntity>()
                .eq(SysRoleMenuEntity::getSysMenuId, id));
    }

    @Override
    public List<?> findNavTree() {
        List<Long> menuIdList = sysRoleMenuMapper.getMenuIdByUserId(LoginUtils.getLoginId());
        List<SysMenuEntity> menuList = sysMenuMapper.selectList(new LambdaQueryWrapper<SysMenuEntity>()
                .eq(SysMenuEntity::getDeleted, false)
                .in(SysMenuEntity::getId, menuIdList));
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("sort");
        treeNodeConfig.setDeep(3);

        return TreeUtil.build(menuList, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getSort().toString());
            tree.setName(treeNode.getName());
            tree.put("path", treeNode.getPath());
            tree.put("component", treeNode.getComponent());
            Map<String, Object> metaMap = MapUtil.newHashMap();
            metaMap.put("title", treeNode.getTitle());
            metaMap.put("icon", treeNode.getIcon());
            metaMap.put("hidden", treeNode.getHidden());
            tree.put("meta", metaMap);
        });
    }

    @Override
    public List<?> findMenuTree() {
        List<SysMenuEntity> menuList = sysMenuMapper.selectList(
                new LambdaQueryWrapper<SysMenuEntity>()
                        .orderByAsc(SysMenuEntity::getSort)
        );

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("sort");
        treeNodeConfig.setDeep(3);

        return TreeUtil.build(menuList, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getSort().toString());
            tree.setName(treeNode.getName());
            tree.put("path", treeNode.getPath());
            tree.put("component", treeNode.getComponent());
            tree.put("title", treeNode.getTitle());
            tree.put("icon", treeNode.getIcon());
            tree.put("hidden", treeNode.getHidden());
        });

    }

    @Override
    public List<?> getMenuTreeOptions() {
        List<SysMenuEntity> menuList = sysMenuMapper.selectList(
                new LambdaQueryWrapper<SysMenuEntity>()
                        .orderByAsc(SysMenuEntity::getSort)
        );

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("sort");
        treeNodeConfig.setDeep(3);

        return TreeUtil.build(menuList, 0L, treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setName(treeNode.getName());
            tree.put("label", treeNode.getTitle());
        });
    }
}
