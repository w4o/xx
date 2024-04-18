package com.github.w4o.xx.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysRoleEntity;
import com.github.w4o.xx.core.entity.SysRoleMenuEntity;
import com.github.w4o.xx.core.entity.SysUserRoleEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.service.dto.sys.role.RoleDTO;
import com.github.w4o.xx.service.mapper.SysRoleMapper;
import com.github.w4o.xx.service.mapper.SysRoleMenuMapper;
import com.github.w4o.xx.service.mapper.SysUserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 角色服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleService extends BaseServiceImpl<SysRoleMapper, SysRoleEntity> implements BaseService<SysRoleEntity> {

    private final SysRoleMapper sysRoleMapper;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 获取分页列表
     *
     * @param page      分页参数
     * @param condition 查询参数
     * @return 分页列表
     */
    public Page<RoleDTO> getPageList(Page<SysRoleEntity> page, Map<String, Object> condition) {
        var pageList = sysRoleMapper.findPage(page, condition);
        handlePageRecord(pageList);
        pageList.getRecords().forEach(dto -> dto.setMenus(
                sysRoleMenuMapper.selectObjs(SysRoleMenuEntity.gw()
                        .eq(SysRoleMenuEntity::getSysRoleId, dto.getRoleId())
                        .select(SysRoleMenuEntity::getSysMenuId))));
        return pageList;
    }

    /**
     * 添加角色
     *
     * @param entity 角色实体
     * @param menus  菜单id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(SysRoleEntity entity, Set<Long> menus) {
        // 判断名称是否重复
        Long count = sysRoleMapper.selectCount(new LambdaQueryWrapper<SysRoleEntity>()
                .eq(SysRoleEntity::getRoleName, entity.getRoleName()));
        if (count > 0) {
            throw new CustomException(ErrorCode.E1008);
        }

        // 添加角色信息
        sysRoleMapper.insert(entity);

        long sysRoleId = entity.getId();

        // 添加角色菜单信息
        for (Long menuId : menus) {
            SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
            sysRoleMenu.setSysMenuId(menuId);
            sysRoleMenu.setSysRoleId(sysRoleId);
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }

    /**
     * 修改角色
     *
     * @param entity 角色实体
     * @param menus  菜单id集合
     */
    public void update(SysRoleEntity entity, Set<Long> menus) {
        SysRoleEntity queryEntity = sysRoleMapper.selectById(entity.getId());
        // 判断数据是否存在
        if (queryEntity == null) {
            throw new CustomException(ErrorCode.E1001);
        }
        // 判断名称是否重复
        Long count = sysRoleMapper.selectCount(new LambdaQueryWrapper<SysRoleEntity>()
                .eq(SysRoleEntity::getRoleName, entity.getRoleName())
                .ne(SysRoleEntity::getId, entity.getId()));
        if (count > 0) {
            throw new CustomException(ErrorCode.E1008);
        }
        BeanUtils.copyProperties(entity, queryEntity);
        sysRoleMapper.updateById(queryEntity);

        // 删除角色菜单
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenuEntity>()
                .eq(SysRoleMenuEntity::getSysRoleId, entity.getId())
        );
        // 添加角色菜单信息
        menus.forEach(menuId -> {
            SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
            sysRoleMenu.setSysMenuId(menuId);
            sysRoleMenu.setSysRoleId(entity.getId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        });
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    public void delete(Long id) {
        // 检查角色下是否有用户
        Long count = sysUserRoleMapper.selectCount(new LambdaQueryWrapper<SysUserRoleEntity>()
                .eq(SysUserRoleEntity::getSysRoleId, id));
        if (count > 0) {
            throw new CustomException(ErrorCode.E1009);
        }
        // 删除角色
        sysRoleMapper.deleteById(id);
        // 同时删除权限对应的角色菜单数据
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenuEntity>()
                .eq(SysRoleMenuEntity::getSysRoleId, id));
    }

    /**
     * 查询全部角色
     *
     * @return 角色集合
     */
    public List<Map<String, Object>> getAll() {
        return sysRoleMapper.selectMaps(new LambdaQueryWrapper<SysRoleEntity>()
                .select(SysRoleEntity::getRoleName, SysRoleEntity::getId));
    }

    /**
     * 添加角色菜单
     *
     * @param roleId  角色ID
     * @param menuIds 菜单ID集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void addRoleMenu(Long roleId, Long[] menuIds) {
        // 删除角色菜单
        sysRoleMenuMapper.delete(SysRoleMenuEntity.gw().eq(SysRoleMenuEntity::getSysRoleId, roleId));
        // 重新添加角色菜单信息
        for (Long menuId : menuIds) {
            SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
            sysRoleMenu.setSysMenuId(menuId);
            sysRoleMenu.setSysRoleId(roleId);
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }
}
