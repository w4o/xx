package com.github.w4o.xx.manage.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 登录信息
 *
 * @author Frank
 */
@Data
@Builder
public class LoginVO {
    private String username;
    private List<String> roles;
    @JsonProperty("token")
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expires;
}
