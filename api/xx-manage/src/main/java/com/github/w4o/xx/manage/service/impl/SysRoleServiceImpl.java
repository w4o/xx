package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysRoleEntity;
import com.github.w4o.xx.core.entity.SysRoleMenuEntity;
import com.github.w4o.xx.core.entity.SysUserRoleEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.manage.dto.sys.role.RoleDTO;
import com.github.w4o.xx.manage.mapper.SysRoleMapper;
import com.github.w4o.xx.manage.mapper.SysRoleMenuMapper;
import com.github.w4o.xx.manage.mapper.SysUserRoleMapper;
import com.github.w4o.xx.manage.param.sys.role.AddRoleMenuParam;
import com.github.w4o.xx.manage.param.sys.role.AddRoleParam;
import com.github.w4o.xx.manage.param.sys.role.ModifyRoleParam;
import com.github.w4o.xx.manage.param.sys.role.RolePageParam;
import com.github.w4o.xx.manage.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 角色服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Page<RoleDTO> getPageList(RolePageParam param) {
        var page = sysRoleMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
        handlePageRecord(page);
        page.getRecords().forEach(dto -> dto.setMenus(
                sysRoleMenuMapper.selectObjs(new LambdaQueryWrapper<SysRoleMenuEntity>()
                        .eq(SysRoleMenuEntity::getSysRoleId, dto.getRoleId())
                        .select(SysRoleMenuEntity::getSysMenuId))));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddRoleParam param) {
        // 判断名称是否重复
        Long count = sysRoleMapper.selectCount(new LambdaQueryWrapper<SysRoleEntity>()
                .eq(SysRoleEntity::getRoleName, param.getRoleName()));
        if (count > 0) {
            throw new CustomException(ErrorCode.E1008);
        }
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        BeanUtils.copyProperties(param, sysRoleEntity);
        // 添加角色信息
        sysRoleMapper.insert(sysRoleEntity);

        long sysRoleId = sysRoleEntity.getId();

        // 添加角色菜单信息
        for (Long menuId : param.getMenus()) {
            SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
            sysRoleMenu.setSysMenuId(menuId);
            sysRoleMenu.setSysRoleId(sysRoleId);
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }

    @Override
    public void update(Long id, ModifyRoleParam param) {
        SysRoleEntity queryEntity = sysRoleMapper.selectById(id);
        // 判断数据是否存在
        if (queryEntity == null) {
            throw new CustomException(ErrorCode.E1001);
        }
        // 判断名称是否重复
        Long count = sysRoleMapper.selectCount(new LambdaQueryWrapper<SysRoleEntity>()
                .eq(SysRoleEntity::getRoleName, param.getRoleName())
                .ne(SysRoleEntity::getId, id));
        if (count > 0) {
            throw new CustomException(ErrorCode.E1008);
        }
        BeanUtils.copyProperties(param, queryEntity);
        sysRoleMapper.updateById(queryEntity);

        // 删除角色菜单
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenuEntity>()
                .eq(SysRoleMenuEntity::getSysRoleId, id)
        );
        // 添加角色菜单信息
        param.getMenus().forEach(menuId -> {
            SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
            sysRoleMenu.setSysMenuId(menuId);
            sysRoleMenu.setSysRoleId(id);
            sysRoleMenuMapper.insert(sysRoleMenu);
        });
    }

    @Override
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

    @Override
    public List<Map<String, Object>> getAll() {
        return sysRoleMapper.selectMaps(new LambdaQueryWrapper<SysRoleEntity>()
                .select(SysRoleEntity::getRoleName, SysRoleEntity::getId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRoleMenu(AddRoleMenuParam param) {
        // 删除角色菜单
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenuEntity>().eq(SysRoleMenuEntity::getSysRoleId, param.getRoleId()));
        // 重新添加角色菜单信息
        for (Long menuId : param.getMenuIds()) {
            SysRoleMenuEntity sysRoleMenu = new SysRoleMenuEntity();
            sysRoleMenu.setSysMenuId(menuId);
            sysRoleMenu.setSysRoleId(param.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }
}
