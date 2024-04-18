package com.github.w4o.xx.service.impl.sys;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysMenuEntity;
import com.github.w4o.xx.core.entity.SysRoleMenuEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.service.mapper.SysMenuMapper;
import com.github.w4o.xx.service.mapper.SysRoleMenuMapper;
import lombok.RequiredArgsConstructor;
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
public class SysMenuService extends BaseServiceImpl<SysMenuMapper, SysMenuEntity> implements BaseService<SysMenuEntity> {

    private final SysRoleMenuMapper sysRoleMenuMapper;


    /**
     * 删除菜单
     *
     * @param id 菜单Id
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(long id) {
        if (baseMapper.exists(SysMenuEntity.gw().eq(SysMenuEntity::getParentId, id))) {
            throw new CustomException(ErrorCode.E1010);
        }
        baseMapper.deleteById(id);
        // 同时删除菜单对应的权限
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenuEntity>()
                .eq(SysRoleMenuEntity::getSysMenuId, id));
    }

    /**
     * 导航菜单树
     *
     * @param isAdmin 是否为管理员
     * @param loginId 登录Id
     * @return 导航菜单树
     */
    public List<Tree<Long>> findNavTree(boolean isAdmin, Long loginId) {
        List<SysMenuEntity> menuList;
        // 判断是否为管理员
        if (isAdmin) {
            menuList = baseMapper.selectList(new LambdaQueryWrapper<SysMenuEntity>()
                    .orderByAsc(SysMenuEntity::getSort));
        } else {
            menuList = baseMapper.selectByUserId(loginId);
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
            metaMap.put("isHide", treeNode.getIsHide());
            metaMap.put("isKeepAlive", treeNode.getIsKeepAlive());
            metaMap.put("isAffix", treeNode.getIsAffix());
            metaMap.put("isIframe", treeNode.getIsIframe());
            metaMap.put("icon", treeNode.getIcon());
            tree.put("meta", metaMap);
        });
    }

    /**
     * 菜单树
     *
     * @return 菜单树
     */
    public List<Tree<Long>> findTableTree() {

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

    /**
     * 获取菜单树数据
     *
     * @return 菜单树数据
     */
    public List<Tree<Long>> getMenuTreeOptions() {
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