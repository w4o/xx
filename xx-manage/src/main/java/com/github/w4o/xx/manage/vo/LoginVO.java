package com.github.w4o.xx.manage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登录信息
 *
 * @author Frank
 */
@Data
@Builder
@Schema(name = "登陆模型")
public class LoginVO {
    @Schema(title = "jwt Token")
    private String accessToken;
    @Schema(title = "刷新 Token，暂无")
    private String refreshToken;
    @Schema(title = "过期时间")
    private LocalDateTime expires;
}
