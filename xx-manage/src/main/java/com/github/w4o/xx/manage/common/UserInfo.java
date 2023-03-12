package com.github.w4o.xx.manage.common;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 登陆用户信息
 *
 * @author Frank
 */
public class UserInfo extends User {

    @Getter
    private final Long userId;

    public UserInfo(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }
}
