package com.github.w4o.xx.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
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
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {

    /**
     * 管理员id
     */
    public static final Long ROOT_ROLE_ID = 1L;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MenuParam param) {
        // 检查名称是否重复
        long count = baseMapper.selectCount(new LambdaQueryWrapper<SysMenuEntity>()
                .eq(SysMenuEntity::getName, param.getName()));
        if (count > 0) {
            throw new CustomException(ErrorCode.E1007);
        }
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        BeanUtils.copyProperties(param, sysMenuEntity);
        baseMapper.insert(sysMenuEntity);
    }

    @Override
    public void update(long id, MenuParam param) {
        SysMenuEntity queryEntity = baseMapper.selectById(id);
        // 判断数据是否存在
        if (queryEntity == null) {
            throw new CustomException(ErrorCode.E1001);
        }
        // 检查名称是否重复
        long count = baseMapper.selectCount(new LambdaQueryWrapper<SysMenuEntity>()
                .eq(SysMenuEntity::getName, param.getName())
                .ne(SysMenuEntity::getId, id));
        if (count > 0) {
            throw new CustomException(ErrorCode.E1007);
        }
        BeanUtils.copyProperties(param, queryEntity);
        baseMapper.updateById(queryEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(long id) {
        Long menuCount = baseMapper.selectCount(new LambdaQueryWrapper<SysMenuEntity>().eq(SysMenuEntity::getParentId, id));
        if (menuCount.compareTo(0L) > 0) {
            throw new CustomException(ErrorCode.E1010);
        }
        baseMapper.deleteById(id);
        // 同时删除菜单对应的权限
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenuEntity>()
                .eq(SysRoleMenuEntity::getSysMenuId, id));
    }

    @Override
    public List<?> findNavTree() {
        List<SysMenuEntity> menuList;
        // 判断是否为管理员
        if (CollUtil.contains(LoginUtils.getLoginInfo().getRoles(), ROOT_ROLE_ID)) {
            menuList = baseMapper.selectList(new LambdaQueryWrapper<SysMenuEntity>()
                    .orderByAsc(SysMenuEntity::getSort));
        } else {
            menuList = baseMapper.selectByUserId(LoginUtils.getLoginId());
        }

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
            // vue
            metaMap.put("title", treeNode.getTitle());
            metaMap.put("isLink", treeNode.getLink());
            metaMap.put("isHide", treeNode.getHidden());
            metaMap.put("isKeepAlive", treeNode.getIsKeepAlive());
            metaMap.put("isAffix", treeNode.getIsAffix());
            metaMap.put("isIframe", treeNode.getIsIframe());
            metaMap.put("icon", treeNode.getIcon());
            // antd pro
            //tree.put("name", treeNode.getTitle());
            //metaMap.put("icon", treeNode.getIcon());
            tree.put("meta", metaMap);
        });
    }

    @Override
    public List<?> findTableTree() {

        List<SysMenuEntity> menuList = baseMapper.selectList(
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
            tree.putAll(BeanUtil.beanToMap(treeNode, false, false));
        });

    }

    @Override
    public List<?> getMenuTreeOptions() {
        List<SysMenuEntity> menuList = baseMapper.selectList(
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