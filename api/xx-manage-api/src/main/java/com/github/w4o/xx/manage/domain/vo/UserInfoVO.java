package com.github.w4o.xx.manage.domain.vo;

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
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "角色")
    private String[] roles;
    @Schema(description = "权限")
    private String[] permissions;
    @Schema(description = "头像")
    private String avatar;
}
