package com.github.w4o.xx.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.w4o.xx.core.entity.SysUserEntity;
import com.github.w4o.xx.manage.common.UserInfo;
import com.github.w4o.xx.manage.mapper.SysUserMapper;
import com.github.w4o.xx.manage.mapper.SysUserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 用户登录认证实现
 *
 * @author Frank
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserMapper sysUserMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUserEntity querySysUser = sysUserMapper.selectOne(new QueryWrapper<SysUserEntity>().lambda()
                .eq(SysUserEntity::getUsername, userName));
        if (querySysUser == null) {
            throw new UsernameNotFoundException("username:" + userName + "不存在");
        }
        if (querySysUser.getStatus() != 1) {
            throw new UsernameNotFoundException("username:" + userName + "已被禁用");
        }
        UserInfo userInfo = new UserInfo(querySysUser.getId(), userName, querySysUser.getPassword(), new ArrayList<>());
        // 查询角色
        userInfo.setRoles(sysUserRoleMapper.findRoleIdByUserId(querySysUser.getId()));
        return userInfo;
    }

}
