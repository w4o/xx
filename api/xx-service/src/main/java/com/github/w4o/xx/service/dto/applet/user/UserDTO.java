package com.github.w4o.xx.service.dto.applet.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Frank
 */
@Data
public class UserDTO {
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "头像")
    private String headImgUrl;
    @Schema(description = "性别")
    private Integer sex;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "省份")
    private String province;
    @Schema(description = "城市")
    private String city;
    @Schema(description = "微信unionId")
    private String unionId;
    @Schema(description = "微信openId")
    private String openId;
    @Schema(description = "推荐人")
    private Long referrer;
    @Schema(description = "推荐人昵称")
    private Long referrerName;
    @Schema(description = "注册时间")
    private LocalDateTime createTime;
    @Schema(description = "最后登录IP")
    private String lastLoginIp;
    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;
}
