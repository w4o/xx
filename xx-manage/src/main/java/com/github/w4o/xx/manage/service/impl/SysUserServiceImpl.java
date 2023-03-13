package com.github.w4o.xx.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.w4o.xx.core.base.service.impl.BaseServiceImpl;
import com.github.w4o.xx.core.entity.SysUserEntity;
import com.github.w4o.xx.core.entity.SysUserRoleEntity;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import com.github.w4o.xx.core.util.AssertUtils;
import com.github.w4o.xx.core.util.BusinessUtils;
import com.github.w4o.xx.manage.common.config.AppConfig;
import com.github.w4o.xx.manage.common.util.LoginUtils;
import com.github.w4o.xx.manage.dto.sys.user.UserPageDTO;
import com.github.w4o.xx.manage.mapper.SysUserMapper;
import com.github.w4o.xx.manage.mapper.SysUserRoleMapper;
import com.github.w4o.xx.manage.param.ChangePasswordParam;
import com.github.w4o.xx.manage.param.sys.user.AddUserParam;
import com.github.w4o.xx.manage.param.sys.user.ModifyUserParam;
import com.github.w4o.xx.manage.param.sys.user.UserPageParam;
import com.github.w4o.xx.manage.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户服务实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final BusinessUtils businessUtils;
    private final AppConfig appConfig;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AddUserParam param) {
        // 检查用户名是否重复
        Long userCount = sysUserMapper.selectCount(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getUsername, param.getUsername()));
        if (userCount.compareTo(0L) > 0) {
            throw new CustomException(ErrorCode.E1006);
        }
        // 添加用户信息
        SysUserEntity sysUserEntity = new SysUserEntity();
        BeanUtils.copyProperties(param, sysUserEntity);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String password = businessUtils.aesDecrypt(appConfig.getAesKey(), param.getPassword());
        sysUserEntity.setPassword(encoder.encode(password));
        sysUserEntity.setStatus(1);
        sysUserMapper.insert(sysUserEntity);

        long userId = sysUserEntity.getId();
        // 添加角色信息
        param.getRoles().forEach(r -> {
            SysUserRoleEntity sysUserRole = new SysUserRoleEntity();
            sysUserRole.setSysUserId(userId);
            sysUserRole.setSysRoleId(r);
            sysUserRoleMapper.insert(sysUserRole);
        });
    }

    @Override
    public void delete(Long id) {
        sysUserMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, ModifyUserParam param) {
        SysUserEntity queryEntity = sysUserMapper.selectById(id);
        AssertUtils.notNull(queryEntity);
        BeanUtil.copyProperties(param, queryEntity);
        sysUserMapper.updateById(queryEntity);

        // 删除角色
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRoleEntity>()
                .eq(SysUserRoleEntity::getSysUserId, id));
        // 重新添加角色信息
        param.getRoles().forEach(r -> {
            SysUserRoleEntity sysUserRole = new SysUserRoleEntity();
            sysUserRole.setSysUserId(id);
            sysUserRole.setSysRoleId(r);
            sysUserRoleMapper.insert(sysUserRole);
        });
    }

    @Override
    public void disable(Long id) {
        SysUserEntity queryEntity = sysUserMapper.selectById(id);
        AssertUtils.notNull(queryEntity);
        queryEntity.setStatus(0);
        sysUserMapper.updateById(queryEntity);
    }

    @Override
    public void enable(Long id) {
        SysUserEntity queryEntity = sysUserMapper.selectById(id);
        AssertUtils.notNull(queryEntity);
        queryEntity.setStatus(1);
        sysUserMapper.updateById(queryEntity);
    }

    @Override
    public Page<UserPageDTO> getPageList(UserPageParam param) {
        var page = sysUserMapper.findPage(new Page<>(param.getPageNo(), param.getPageSize()), param);
        handlePageRecord(page);
        page.getRecords().forEach(dto -> dto.setRoleList(sysUserRoleMapper.findRoleByUserId(dto.getUserId())));
        return page;
    }

    @Override
    public void resetPassword(long userId) {
        var queryEntity = sysUserMapper.selectById(userId);
        AssertUtils.notNull(queryEntity);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        queryEntity.setPassword(encoder.encode("A123456"));
        sysUserMapper.updateById(queryEntity);
    }

    @Override
    public void changePassword(ChangePasswordParam param) {
        var queryEntity = sysUserMapper.selectById(LoginUtils.getLoginId());
        AssertUtils.notNull(queryEntity);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        String oldPassword = businessUtils.aesDecrypt(appConfig.getAesKey(), param.getOldPassword());
        String newPassword = businessUtils.aesDecrypt(appConfig.getAesKey(), param.getNewPassword());

        if (!encoder.matches(oldPassword, queryEntity.getPassword())) {
            throw new CustomException(ErrorCode.E1012);
        }
        queryEntity.setPassword(encoder.encode(newPassword));
        sysUserMapper.updateById(queryEntity);
    }
}
