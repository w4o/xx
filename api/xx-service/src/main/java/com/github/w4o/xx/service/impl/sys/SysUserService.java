package com.github.w4o.xx.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.BaseService;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysUserEntity;
import com.github.w4o.xx.core.entity.SysUserRoleEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.service.dto.sys.user.UserDTO;
import com.github.w4o.xx.service.mapper.SysUserMapper;
import com.github.w4o.xx.service.mapper.SysUserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 系统用户服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserService extends BaseServiceImpl<SysUserMapper, SysUserEntity> implements BaseService<SysUserEntity> {

    private final SysUserMapper sysUserMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    /**
     * 添加用户
     *
     * @param entity 用户实体
     * @param roles  角色id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(SysUserEntity entity, Set<Long> roles) {
        // 检查用户名是否重复
        Long userCount = sysUserMapper.selectCount(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getUsername, entity.getUsername()));
        if (userCount.compareTo(0L) > 0) {
            throw new CustomException(ErrorCode.E1006);
        }
        // 添加用户信息

        sysUserMapper.insert(entity);

        long userId = entity.getId();
        // 添加角色信息
        roles.forEach(r -> {
            SysUserRoleEntity sysUserRole = new SysUserRoleEntity();
            sysUserRole.setSysUserId(userId);
            sysUserRole.setSysRoleId(r);
            sysUserRoleMapper.insert(sysUserRole);
        });
    }


    /**
     * 修改用户
     *
     * @param entity 用户实体
     * @param roles  角色id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserEntity entity, Set<Long> roles) {
        SysUserEntity queryEntity = sysUserMapper.selectById(entity.getId());
        AssertUtils.notNull(queryEntity);
        BeanUtils.copyProperties(entity, queryEntity);
        sysUserMapper.updateById(queryEntity);

        // 删除角色
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRoleEntity>()
                .eq(SysUserRoleEntity::getSysUserId, entity.getId()));
        // 重新添加角色信息
        roles.forEach(r -> {
            SysUserRoleEntity sysUserRole = new SysUserRoleEntity();
            sysUserRole.setSysUserId(entity.getId());
            sysUserRole.setSysRoleId(r);
            sysUserRoleMapper.insert(sysUserRole);
        });
    }


    /**
     * 获取分页列表
     *
     * @param page   分页信息
     * @param entity 查询条件
     * @return 分页列表
     */
    public Page<UserDTO> getPageList(Page<SysUserEntity> page, SysUserEntity entity) {
        var pageList = sysUserMapper.findPage(page, entity);
        handlePageRecord(pageList);
        pageList.getRecords().forEach(dto -> dto.setRoleList(sysUserRoleMapper.findRoleByUserId(dto.getUserId())));
        return pageList;
    }

}
