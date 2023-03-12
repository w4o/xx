package com.github.w4o.xx.manage.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 用户信息
 *
 * @author Frank
 */
@Data
@Builder
public class UserInfoVO {
    private String username;
    private String[] roles;
    private String[] permissions;
    private String avatar;
}
