package com.github.w4o.xx.manage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 用户信息
 *
 * @author Frank
 */
@Data
@Builder
@Schema(name = "用户信息模型")
public class UserInfoVO {
    @Schema(title = "用户名")
    private String username;
    @Schema(title = "角色")
    private String[] roles;
    @Schema(title = "权限")
    private String[] permissions;
    @Schema(title = "头像")
    private String avatar;
}
