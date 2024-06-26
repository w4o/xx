package com.github.w4o.xx.manage.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * 登录用户信息
 *
 * @author Frank
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends User {

    private final Long userId;
    @Setter
    private List<Long> roles;

    public UserInfo(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }
}
